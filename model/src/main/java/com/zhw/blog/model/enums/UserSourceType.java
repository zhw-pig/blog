package com.zhw.blog.model.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhanghuaiwei
 * @date 2024/12/31 9:39
 * 登录用户来源 0：后台管理员；1：客户端用户
 */
@AllArgsConstructor
@Getter
public enum UserSourceType implements BaseEnum {

    ADMIN(0, "后台管理员"),

    CLIENT(1, "客户端用户");

    @EnumValue
    private Integer code;
    private String description;

    @JsonValue
    public HashMap<String, Object> JSON() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("description", description);
        return map;
    }

    @JsonCreator
    public static UserSourceType getByCode(Object data) {
        Integer code = 0;
        if (data instanceof Map) {
            code = (Integer)((Map)data).getOrDefault("code",0);
        } else if (data instanceof Integer) {
            code = (Integer)data;
        }
        for (UserSourceType userSourceType : values()) {
            if (userSourceType.code.equals(code)) {
                return userSourceType;
            }
        }
        return UserSourceType.ADMIN;
    }

}
