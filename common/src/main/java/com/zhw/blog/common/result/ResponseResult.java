package com.zhw.blog.common.result;

import lombok.Data;

/**
 * 全局统一返回结果类
 */
@Data
public class ResponseResult<T> {

    //返回码
    private Integer code;

    //返回消息
    private String message;

    //返回数据
    private T data;

    public ResponseResult() {
    }

    private static <T> ResponseResult<T> build(T data) {
        ResponseResult<T> result = new ResponseResult<>();
        if (data != null)
            result.setData(data);
        return result;
    }

    public static <T> ResponseResult<T> build(T body, ResultCodeEnum resultCodeEnum) {
        ResponseResult<T> result = build(body);
        result.setCode(resultCodeEnum.getCode());
        result.setMessage(resultCodeEnum.getMessage());
        return result;
    }


    public static <T> ResponseResult<T> ok(T data) {
        return build(data, ResultCodeEnum.SUCCESS);
    }

    public static <T> ResponseResult<T> ok() {
        return ResponseResult.ok(null);
    }

    public static <T> ResponseResult<T> fail() {
        return build(null, ResultCodeEnum.FAIL);
    }

    // 错误异常处理
    public static <T> ResponseResult<T> fail(Integer code, String message) {
        ResponseResult<T> result = build(null);
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
