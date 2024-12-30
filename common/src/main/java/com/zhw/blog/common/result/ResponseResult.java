package com.zhw.blog.common.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 全局统一返回结果类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {

    //返回码
    private Integer code;

    //返回消息
    private String message;

    //返回数据
    private T data;





    public static ResponseResult errorResult(int code, String message) {
        ResponseResult result = new ResponseResult();
        return result.error(code, message);
    }

    public static ResponseResult okResult() {
        ResponseResult result = new ResponseResult();
        return result.ok(ResultCodeEnum.SUCCESS.getCode(), null, ResultCodeEnum.SUCCESS.getMessage());
    }

    public static ResponseResult okResult(int code, String message) {
        ResponseResult result = new ResponseResult();
        return result.ok(code, null, message);
    }

    public static ResponseResult okResult(Object data) {
        ResponseResult result = setAppHttpCodeEnum(ResultCodeEnum.SUCCESS,
                ResultCodeEnum.SUCCESS.getMessage());
        if (data != null) {
            result.setData(data);
        }
        return result;
    }

    public static ResponseResult errorResult(ResultCodeEnum enums) {
        return setAppHttpCodeEnum(enums, enums.getMessage());
    }

    public static ResponseResult errorResult(ResultCodeEnum enums, String message) {
        return setAppHttpCodeEnum(enums, message);
    }

    public static ResponseResult setAppHttpCodeEnum(ResultCodeEnum enums) {
        return okResult(enums.getCode(), enums.getMessage());
    }

    private static ResponseResult setAppHttpCodeEnum(ResultCodeEnum enums,
                                                     String message) {
        return okResult(enums.getCode(), message);
    }

    public ResponseResult<?> error(Integer code, String message) {
        this.code = code;
        this.message = message;
        return this;
    }

    public ResponseResult<?> ok(Integer code, T data) {
        this.code = code;
        this.data = data;
        return this;
    }

    public ResponseResult<?> ok(Integer code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
        return this;
    }

    public ResponseResult<?> ok(T data) {
        this.data = data;
        return this;
    }
}
