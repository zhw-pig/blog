package com.zhw.blog.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhw.blog.common.config.JwtConfig;
import com.zhw.blog.common.exception.BlogException;
import com.zhw.blog.common.result.ResultCodeEnum;
import com.zhw.blog.model.entity.Admin;
import com.zhw.blog.model.enums.BaseStatus;
import com.zhw.blog.web.admin.mapper.AuthMapper;
import com.zhw.blog.web.admin.service.AuthService;
import com.zhw.blog.web.admin.vo.login.AdminLoginVo;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhanghuaiwei
 * @date 2024/12/27 10:34
 */
@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Admin>
        implements AuthService {

    @Autowired
    private AuthMapper authMapper;
    @Autowired
    private JwtConfig jwtConfig;


    @Override
    public String authToken(AdminLoginVo adminLoginVo) {
        // 接口参数判空
        if(adminLoginVo == null) {
            throw new BlogException(ResultCodeEnum.PARAM_ERROR);
        }
        if(StringUtil.isNullOrEmpty(adminLoginVo.getUsername())) {
            throw new BlogException(ResultCodeEnum.ACCOUNT_NULL);
        }
        if(StringUtil.isNullOrEmpty(adminLoginVo.getPassword())) {
            throw new BlogException(ResultCodeEnum.PASSWORD_NULL);
        }
        // 判断该账号是否在数据库中
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getLoginId, adminLoginVo.getUsername());
        if(!authMapper.exists(queryWrapper)) {
            throw new BlogException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 判断该账号是否被禁用
        if(authMapper.selectOne(queryWrapper).getState() == BaseStatus.DISABLE) {
            throw new BlogException(ResultCodeEnum.ACCOUNT_DISABLE);
        }
        // 判断密码是否正确
        if(!authMapper.selectOne(queryWrapper).getPassword().equals(adminLoginVo.getPassword())) {
            throw new BlogException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 生成token
        String token = jwtConfig.createJWT(adminLoginVo.getUsername());
        return token;
    }
}
