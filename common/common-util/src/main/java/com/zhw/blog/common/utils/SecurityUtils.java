package com.zhw.blog.common.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 * Security工具类
 */
@Configuration
public class SecurityUtils {

    @Autowired
    private AuthenticationManager authenticationManager;


    /**
     * 获取认证权限信息
     **/
    public Authentication getAuthentication(String username, String password) {
        // 使用SpringSecurity的AuthenticationManager 进行身份认证
        // authenticationManager的认证方法authenticate，接收一个Authentication类型的参数
        // Authentication：是接口类型
        // 所以这块使用Authentication他的实现类UsernamePasswordAuthenticationToken
        // alt+ctrl+点击Authentication：可查看实现类
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username,password);
        // 把客户端传的用户名和密码封装成authenticationToken
        // 通过authenticationManager调用authenticate
        // 可执行在自定义的AdminDetailsServiceImpl重写的loadUserByUsername方法
        // 从而实现用户的登录认证
        return authenticationManager.authenticate(authenticationToken);
    }

    /**
     * 将每次接口携带的token的用户信息，存入SecurityContextHolder;
     * 方便异步获取当前登录用户信息
     * 参数1：用户名；参数2： 密码；参数3：权限信息
     * 三个参数构造器：表示已认证状态的（super.setAuthenticated(true)）
     **/
    public static void setAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(principal, credentials, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    /**
     * 后台用户登录：
     * 获取登录账号：loginId/username
     * setAuthentication: 向SecurityContextHolder存储的是username，而不是整个AdminLoginSecurity
     **/
    public static String getAdminUsername() {
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        // UserLoginSecurity adminLoginSecurity = (UserLoginSecurity) authentication.getPrincipal();
        // return adminLoginSecurity.getUsername();
        return (String) authentication.getPrincipal();
    }

}