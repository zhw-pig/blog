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
 * @date 2024/12/26 15:10
 * 角色类型，0角色，1分组
 */
@AllArgsConstructor
@Getter
public enum RoleType implements BaseEnum {

    ROLE(0, "角色"),
    ROLE_GROUP(1, "分组");


    @EnumValue // 表示在数据库中存储和读取时使用这个属性的值
    private Integer code;

    private String description;


    // 返回给客户端的数据
    @JsonValue
    public HashMap<String, Object> JSON() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", code);
        map.put("description", description);
        return map;
    }

    @JsonCreator
    public static RoleType getByCode(Object data) {
        Integer code = 0;
        if (data instanceof Map) {
            code = (Integer)((Map)data).getOrDefault("code",0);
        } else if (data instanceof Integer) {
            code = (Integer)data;
        }
        for (RoleType adminType : values()) {
            if (adminType.code.equals(code)) {
                return adminType;
            }
        }
        return RoleType.ROLE;
    }

}
