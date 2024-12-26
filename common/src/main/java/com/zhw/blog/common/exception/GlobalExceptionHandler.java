package com.zhw.blog.common.exception;

import com.zhw.blog.common.result.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description:  处理所有Controller方法抛出的异常， 因此Controller层就无序关注异常的处理逻辑了
 * @author: zhanghuaiwei
 * @date: 2024/11/16 17:45
 **/

// RestControllerAdvice： @ControllerAdvice + @ResponseBody
@RestControllerAdvice  // 用于声明处理全局Controller方法异常的类
@Slf4j

// @ControllerAdvice: 用于声明处理全局Controller方法异常的类
// @ExceptionHandler: 用于声明处理异常的方法，`value`属性用于声明该方法处理的异常类型
// @ResponseBody: 表示将方法的返回值作为HTTP的响应体
public class GlobalExceptionHandler {

    @ExceptionHandler(SystemException.class)
    public ResponseResult systemExceptionHandler(SystemException e){
        // 打印异常信息
        log.error("出现了异常！ {}", e);
        e.printStackTrace();
        return ResponseResult.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(Exception.class)  // 用于声明处理异常的方法，`value`属性用于声明该方法处理的异常类型
    public ResponseResult exceptionHandler(Exception e){
        // 打印异常信息
        log.error("出现了异常！ {}", e);
        e.printStackTrace();
        return ResponseResult.fail();
    }
}