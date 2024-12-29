package com.zhw.blog.common.config;



import com.zhw.blog.common.converter.StringToBaseEnumConverterFactory;
import com.zhw.blog.common.interceptor.AuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableConfigurationProperties(AuthProperties.class)
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private StringToBaseEnumConverterFactory stringToBaseEnumConverterFactory;
    // @Autowired
    // private AuthenticationInterceptor authenticationInterceptor;
    // @Autowired
    // private AuthProperties authProperties;

    // 序列化转换器
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverterFactory(this.stringToBaseEnumConverterFactory);
    }


    // 在WVC配置中，注册拦截器
    // @Override
    // public void addInterceptors(InterceptorRegistry registry) {
    //     registry.addInterceptor(this.authenticationInterceptor)
    //             // 拦截所有/admin/路径请求
    //             .addPathPatterns(authProperties.getAddPathPatterns())
    //             // 不拦截/admin/login/路径请求
    //             .excludePathPatterns(authProperties.getExcludePathPatterns());
    // }
}