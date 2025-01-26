package com.zhw.blog.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhw.blog.acl.mapper.UserMapper;
import com.zhw.blog.acl.service.UserService;
import com.zhw.blog.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
* @author zhw
* @description 针对表【t_sys_user】的数据库操作Service实现
* @createDate 2024-12-26 11:57:37
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getAdminByUsername(String username) {
        LambdaQueryWrapper<User> adminQueryWrapper = new LambdaQueryWrapper<>();
        adminQueryWrapper.eq(StringUtils.hasText(username), User::getLoginId, username);
        User admin = userMapper.selectOne(adminQueryWrapper);
        return admin;
    }
}