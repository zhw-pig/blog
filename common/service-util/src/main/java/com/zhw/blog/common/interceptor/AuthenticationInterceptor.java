package com.zhw.blog.common.interceptor;

import com.zhw.blog.common.config.JwtConfig;
import com.zhw.blog.common.config.JwtPropertyConfig;
import com.zhw.blog.common.constant.AuthConstant;
import com.zhw.blog.common.constant.RedisConstant;
import com.zhw.blog.common.exception.BlogException;
import com.zhw.blog.common.result.ResultCodeEnum;
import com.zhw.blog.common.utils.RedisCacheUtil;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 为所有受保护的接口增加校验JWT合法性的逻辑
// @Component // 注释掉： 不再注册到容器中
// HandlerInterceptor 是Spring MVC框架的一部分，主要用于拦截请求和响应
// HandlerInterceptor和SpringSecurity的区别：
// HandlerInterceptor 适用于简单的权限控制和请求级别的拦截，实现简单，适用于轻量级应用。
// Spring Security 提供了全面的安全控制解决方案，适用于复杂应用和企业级应用，功能强大且灵活。

@SuppressWarnings("all")
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private RedisCacheUtil redisCacheUtil;
    @Autowired
    private JwtPropertyConfig jwtPropertyConfig;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String jwtHeader = StringUtils.hasText(jwtPropertyConfig.getHeader())
                ? jwtPropertyConfig.getHeader()
                : AuthConstant.JWT_HEADER;
        String token = request.getHeader(jwtHeader);
        // 去掉请求头Authorization携带的Bearer 前缀
        if(token.startsWith(jwtPropertyConfig.getPrefix())) {
            token = token.substring(jwtPropertyConfig.getPrefix().length());
        }
        // 为所有受保护的接口校验jwt
        Claims claims = jwtConfig.parseToken(token);
        // 为避免重复解析，通常会在拦截器将Token解析完毕后，将结果保存至**ThreadLocal**中，这样一来，我们便可以在整个请求的处理流程中进行访问了。
        String loginId = claims.getSubject();
        // 将jwt解析的用户信息保持到redis里面，方便后续的权限校验
        String key = RedisConstant.ADMIN_LOGIN_PREFIX + loginId;
        // 获取redis保存的token
        String redisToken = redisCacheUtil.getCacheObject(key);
        if(!StringUtils.hasText(redisToken)){
            throw new BlogException(ResultCodeEnum.TOKEN_EXPIRED);
        }
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}