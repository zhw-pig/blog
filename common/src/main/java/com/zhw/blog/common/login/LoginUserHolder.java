package com.zhw.blog.common.login;

/**
 * @author zhanghuaiwei
 * @date 2024/11/19 13:38
 */
public class LoginUserHolder {
    public static ThreadLocal<LoginUser> threadLocal = new ThreadLocal<>();

    public static void setLoginUser(LoginUser loginUser) {
        threadLocal.set(loginUser);
    }

    public static LoginUser getLoginUser() {
        return threadLocal.get();
    }

    public static void clear() {
        threadLocal.remove();
    }
}
