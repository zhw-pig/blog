package com.zhw.blog.common.exception;

import com.zhw.blog.common.result.ResultCodeEnum;
import lombok.Data;

/**
 * @author zhanghuaiwei
 * @date 2024/11/16 17:39
 * 想要直接为前端返回响应，可利用全局异常处理功能
 */
@SuppressWarnings("all")
@Data
// 想要直接为前端返回响应，可利用全局异常处理功能
public class BlogException extends RuntimeException {

    //异常状态码
    private Integer code;
    /**
     * 通过状态码和错误消息创建异常对象
     * @param message
     * @param code
     */
    public BlogException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    /**
     * 根据响应结果枚举对象创建异常对象
     * @param resultCodeEnum
     */
    public BlogException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public String toString() {
        return "SystemException{" +
                "code=" + code +
                ", message=" + this.getMessage() +
                '}';
    }
}