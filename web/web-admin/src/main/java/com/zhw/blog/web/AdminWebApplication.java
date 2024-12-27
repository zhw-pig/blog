package com.zhw.blog.web;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


/**
 * @description:
 * @author: zhw
 * @date: 2024/12/26 12:10
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.zhw.blog.common", "com.zhw.blog.model", "com.zhw.blog.web.admin"})
public class AdminWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminWebApplication.class, args);
    }
}
