package com.zhw.blog.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhw.blog.common.config.JwtConfig;
import com.zhw.blog.common.config.JwtPropertyConfig;
import com.zhw.blog.common.constant.AuthConstant;
import com.zhw.blog.common.constant.RedisConstant;
import com.zhw.blog.common.exception.BlogException;
import com.zhw.blog.common.result.ResponseResult;
import com.zhw.blog.common.result.ResultCodeEnum;
import com.zhw.blog.common.security.UserLoginSecurity;
import com.zhw.blog.common.util.RedisCacheUtil;
import com.zhw.blog.common.util.SecurityUtils;
import com.zhw.blog.common.util.WebUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author zhanghuaiwei
 * @date 2024/12/29 15:50
 * @description jwt过滤器
 * OncePerRequestFilter： 保证每个接口只会过滤一次
 */
@Configuration
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtPropertyConfig jwtPropertyConfig;
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private RedisCacheUtil redisCacheUtil;
    @Resource
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中的token
        String jwtHeader = StringUtils.hasText(jwtPropertyConfig.getHeader())
                ? jwtPropertyConfig.getHeader()
                : AuthConstant.JWT_HEADER;
        String token = request.getHeader(jwtHeader);
        if (!StringUtils.hasText(token)) {
            // 说明该接口不需要登录 直接放行
            filterChain.doFilter(request, response);
            // 如果不return，放行之后，响应的时候，还会继续执行下面的过滤器逻辑
            return;
        }
        // 去掉请求头Authorization携带的Bearer 前缀
        if(token.startsWith(jwtPropertyConfig.getPrefix())) {
            token = token.substring(jwtPropertyConfig.getPrefix().length());
        }
        // 解析token携带的用户信息
        Claims claims = null;
        try {
            claims = jwtConfig.parseToken(token);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BlogException(ResultCodeEnum.TOKEN_INVALID);
        }
        String loginId = claims.getSubject();
        // 从redis中获取用户信息
        String key = RedisConstant.ADMIN_LOGIN_PREFIX + loginId;
        UserLoginSecurity userLoginSecurity = redisCacheUtil.getCacheObject(key);
        if(Objects.isNull(userLoginSecurity)){
            // throw new BlogException(ResultCodeEnum.NEED_LOGIN);
            // token超时 token非法
            // 响应告诉前端需要重新登录
            ResponseResult result = ResponseResult.errorResult(ResultCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, objectMapper.writeValueAsString(result));
            return;
        }
        // 将每次接口携带的token的用户信息，存入SecurityContextHolder
        // 方便异步获取当前登录用户信息
        // 参数1：用户名；参数2： 密码；参数3：权限信息
        // 三个参数构造器：表示已认证状态的（super.setAuthenticated(true)）
        // UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginId, null, null);
        // SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 这块不能用注入，否则会形成依赖循环, 所以setAuthentication写成静态的，直接调用
        SecurityUtils.setAuthentication(loginId, null, userLoginSecurity.getAuthorities());
        // 携带token的接口放行
        filterChain.doFilter(request, response);
    }
}
