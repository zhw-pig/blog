package com.zhw.blog.common.util;

import com.zhw.blog.common.security.AdminLoginSecurity;
import com.zhw.blog.model.enums.AdminType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    /**
     * 设置Authentication
     */
    public static void setAuthentication(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取用户  admin和user分开写
     **/
    // public static <T>T getLoginUser(Class<T> clazz) {
    //     AdminLoginSecurity adminLoginSecurity = (AdminLoginSecurity)getAuthentication().getPrincipal();
    //     if (clazz.isInstance(adminLoginSecurity)) {
    //         return clazz.cast(adminLoginSecurity);
    //     }
    //     throw new IllegalArgumentException("Principal is not of type " + clazz.getName());
    // }

    public static AdminLoginSecurity getLoginAdmin() {
        return (AdminLoginSecurity)getAuthentication().getPrincipal();
    }

    public static Boolean isAdmin() {
        return getLoginAdmin().getAdmin().getIsAdmin() == AdminType.IS_ADMIN;
    }

    // LoginId指登录id/手机号
    public static String getAdminLoginId() {
        return getLoginAdmin().getUsername();
    }
}