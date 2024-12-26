package com.zhw.blog.common.interceptor;

import com.zhw.blog.common.config.JwtConfig;
import com.zhw.blog.common.login.LoginUser;
import com.zhw.blog.common.login.LoginUserHolder;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 为所有受保护的接口增加校验JWT合法性的逻辑
@Component

@SuppressWarnings("all")
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtConfig jwtConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("access-token");
        // 为所有受保护的接口校验jwt
        Claims claims = jwtConfig.parseToken(token);
        // 为避免重复解析，通常会在拦截器将Token解析完毕后，将结果保存至**ThreadLocal**中，这样一来，我们便可以在整个请求的处理流程中进行访问了。
        String userId = claims.getSubject();
        // 将jwt解析的用户信息保存到ThreadLocal
        LoginUserHolder.setLoginUser(new LoginUser(Long.parseLong(userId)));
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        LoginUserHolder.clear();
    }
}