package com.zhw.blog.web;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


/**
 * @description:
 * @author: zhw
 * @date: 2024/12/26 12:10
 **/
@SpringBootApplication
@ComponentScan(basePackages = {"com.zhw.blog.common", "com.zhw.blog.model", "com.zhw.blog.web.admin"})
// 开启Security权限功能
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AdminWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminWebApplication.class, args);
    }
}
