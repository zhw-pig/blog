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
 * 角色分组类型（只有自定义分组才可编辑）
 * 0：博客管理角色
 * 1：博客pc客户端角色
 * 2：博客app客户端角色
 * 3：博客小程序客户端角色
 * 4：自定义角色
 */
@AllArgsConstructor
@Getter
public enum RoleClassifyType implements BaseEnum {

    BLOG_MANAGE_ROLE(0, "博客管理角色"),
    BLOG_CLINT_PC_ROLE(1, "博客pc客户端角色"),
    BLOG_CLINT_APP_ROLE(2, "博客app客户端角色"),
    BLOG_CLINT_APPLET_ROLE(3, "博客小程序客户端角色"),
    BLOG_CUSTOM_ROLE(4, "自定义角色");


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
    public static RoleClassifyType getByCode(Object data) {
        Integer code = 0;
        if (data instanceof Map) {
            code = (Integer)((Map)data).getOrDefault("code",0);
        } else if (data instanceof Integer) {
            code = (Integer)data;
        }
        for (RoleClassifyType adminType : values()) {
            if (adminType.code.equals(code)) {
                return adminType;
            }
        }
        return RoleClassifyType.BLOG_MANAGE_ROLE;
    }

}
