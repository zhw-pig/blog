package com.zhw.blog.common.interceptor;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author zhanghuaiwei
 * @date 2024/12/4 16:13
 */
@ConfigurationProperties(prefix = "auth.interceptor")
@Data
public class AuthProperties {
    // 拦截的请求
    private String addPathPatterns;
    // 放开的请求
    private String excludePathPatterns;
}
