package com.zhw.blog;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


//权限管理模块启动类
@SpringBootApplication
// @EnableDiscoveryClient： spring Cloud 提供的一个注解，用于启用服务发现客户端功能。
// 它通常与服务注册与发现组件（如 Eureka、Consul 或 Nacos）一起使用
@EnableDiscoveryClient  // 集成nacos
public class ServiceAclApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceAclApplication.class, args);
    }
}
