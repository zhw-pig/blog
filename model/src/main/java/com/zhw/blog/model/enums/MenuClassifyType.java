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
 * 分组：0、业务资源菜单；1、系统资源菜单
 */
@AllArgsConstructor
@Getter
public enum MenuClassifyType implements BaseEnum {

    BLOG_MENU(0, "业务资源菜单"),
    SYS_MENU(1, "系统资源菜单");


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
    public static MenuClassifyType getByCode(Object data) {
        Integer code = 0;
        if (data instanceof Map) {
            code = (Integer)((Map)data).getOrDefault("code",0);
        } else if (data instanceof Integer) {
            code = (Integer)data;
        }
        for (MenuClassifyType adminType : values()) {
            if (adminType.code.equals(code)) {
                return adminType;
            }
        }
        return MenuClassifyType.SYS_MENU;
    }

}
