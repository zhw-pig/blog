package com.zhw.blog.web;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * @description:
 * @author: zhw
 * @date: 2024/12/26 12:10
 **/
@SpringBootApplication
@MapperScan("com.zhw.blog.web.admin.mapper")
public class AdminWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminWebApplication.class, args);
    }
}
