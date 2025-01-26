package com.zhw.blog.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhw.blog.acl.mapper.AuthMapper;
import com.zhw.blog.common.exception.BlogException;
import com.zhw.blog.common.result.ResultCodeEnum;
import com.zhw.blog.common.security.UserLoginSecurity;
import com.zhw.blog.model.entity.User;
import com.zhw.blog.model.enums.BaseStatus;
import com.zhw.blog.acl.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;


/**
 * @author zhanghuaiwei
 * @date 2024/12/29 11:27
 * @description 实现UserDetailsService ，重写loadUserByUsername，
 *              更改默认从内存读取用户登录security
 */
// ServiceImpl  需要使用@Service注册
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private MenuMapper menuMapper;


    // 被authenticationManager.authenticate(authenticationToken)执行
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1、认证
        // 判断用户名是否为空
        if(!StringUtils.hasText(username)) {
            throw new BlogException(ResultCodeEnum.ACCOUNT_NULL);
        }
        // 根据用户名查询用户信息
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(username), User::getLoginId, username);
        User user = authMapper.selectOne(queryWrapper);
        // 判断该账号是否在数据库中
        if(Objects.isNull(user)) {
            throw new BlogException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 判断该账号是否被禁用
        if(user.getState() == BaseStatus.DISABLE) {
            // 替换SpringSecurity默认的账号禁用提示信息: User is Disabled
            throw new BlogException(ResultCodeEnum.ACCOUNT_DISABLE);
        }
        // 2、授权: 查询用户的对应权限信息
        // List<String> permissions = menuMapper.selectPermissionByUserId(user.getId());
        // 3、返回用户信息（UserDetails接口的自定义实现类）
        // Authentication认证成功后，会返回一个UserDetails对象
        return new UserLoginSecurity(user, null);
    }
}
