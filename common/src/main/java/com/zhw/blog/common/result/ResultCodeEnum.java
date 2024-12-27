package com.zhw.blog.common.result;

import lombok.Getter;

/**
 * 统一返回结果状态信息类
 */
@Getter
public enum ResultCodeEnum {

    SUCCESS(200, "操作成功"),
    FAIL(201, "操作失败"),
    PARAM_ERROR(400, "参数不正确"),
    TOKEN_EXPIRED(401, "token过期"),
    TOKEN_INVALID(402, "token非法"),

    NO_LOGIN(501, "未登陆"),
    ACCOUNT_NULL(502, "账号不能为空"),
    PASSWORD_NULL(503, "密码不能为空"),
    LOGIN_ERROR(504, "账号或密码错误"),
    ACCOUNT_DISABLE(503, "账号已禁用");


    private final Integer code;

    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
