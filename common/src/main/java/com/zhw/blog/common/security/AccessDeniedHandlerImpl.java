package com.zhw.blog.common.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhw.blog.common.result.ResponseResult;
import com.zhw.blog.common.result.ResultCodeEnum;
import com.zhw.blog.common.util.WebUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

 /**
    * 授权失败处理器：
    * 向容器注册实现了AccessDeniedHandler接口的Security访问拒绝处理器类，
    * 在Security配置类中配置了该实现类，当访问拒绝时，该类会被调用
 */
@Configuration
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Resource
    private ObjectMapper objectMapper;
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        accessDeniedException.printStackTrace();
        ResponseResult result = ResponseResult.errorResult(HttpStatus.FORBIDDEN.value(), ResultCodeEnum.NO_OPERATOR_AUTH.getMessage());
        // 响应给前端
        WebUtils.renderString(response, objectMapper.writeValueAsString(result));
    }
}