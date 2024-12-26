package com.zhw.blog.model.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @description: BaseEnum用作系统枚举规范接口
 * @author: zhw
 * @date: 2024/12/26 10:19
 */


public interface BaseEnum {

    Integer getCode();

    String getDescription();
}
