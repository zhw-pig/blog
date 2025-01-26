package com.zhw.blog.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhanghuaiwei
 * @date 2024/11/18 17:30
 */
// 获取不到当前目录下的jwt.yml文件
// 只能获取主程序所在的yml文件
// TODO: 需要兼容前后端的动态配置   目前只支持一端的jwt配置
// 会主动读取不同系统的application.yml
@ConfigurationProperties(prefix = "jwt")
@Data
// @ConditionalOnResource(resources = "classpath:jwt.yml")
// @EnableConfigurationProperties(JwtConfig.class)
@Component
public class JwtPropertyConfig {
    //有效期为
    private Long expiration;
    //设置秘钥明文
    private String secret;
    // 签发者
    private String issuer;
    // 密钥算法
    private String algorithm;
    // 请求头
    private String header;
    // token前缀
    private String prefix;
}
