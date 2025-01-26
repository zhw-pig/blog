package com.zhw.blog.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhw.blog.common.result.ResponseResult;
import com.zhw.blog.common.result.ResultCodeEnum;
import com.zhw.blog.common.utils.WebUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理类
 * 向容器注册实现了AuthenticationEntryPoint接口的Security认证失败处理类，
 * 在Security配置类中配置了该类，当认证失败时，该类会被调用
 */
@Configuration
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Resource
    private ObjectMapper objectMapper;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        authException.printStackTrace();
        ResponseResult result = null;
        if (authException instanceof BadCredentialsException) {
            result = ResponseResult.errorResult(ResultCodeEnum.LOGIN_ERROR.getCode(), authException.getMessage());
        } else if (authException instanceof InsufficientAuthenticationException) {
            result = ResponseResult.errorResult(ResultCodeEnum.NEED_LOGIN);
        } else {
            result = ResponseResult.errorResult(ResultCodeEnum.SYSTEM_ERROR.getCode(), "认证或授权失败");
        }
        // 响应给前端
        WebUtils.renderString(response, objectMapper.writeValueAsString(result));
    }
}