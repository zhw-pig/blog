package com.zhw.blog.acl.config;

import com.zhw.blog.acl.service.impl.UserDetailsServiceImpl;
import com.zhw.blog.common.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * @author zhanghuaiwei
 * @date 2024/12/29 13:06
 * @description SpringSecurity的自定义配置
 */
@Configuration
@EnableWebSecurity  // 这个需要添加，不然http会注入失败
// extends WebSecurityConfigurerAdapter 在Spring Security 5.7.0-M2及更高版本中已被弃用，
// 推荐使用基于 SecurityFilterChain 的配置方式
public class SecurityConfig  {

    @Autowired
    private UserDetailsServiceImpl adminDetailsService;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    // 因为系统实现了AuthenticationEntryPoint接口，所以可以注入该接口，从而实现自定义的认证失败处理逻辑
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

            // 前后端不分离，信息存储到了cookie中，会有csrf攻击的风险
            // 前后端分离：是不需要考虑csrf攻击的，如果不关闭csrf，则默认会校验请求是否携带了csrf_token
            // 所以关闭csrf：跨站请求伪造攻击
            .csrf().disable()
            // 前后端分离：关闭session,不通过session获取SecurityContext
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests()
            // 对于登录接口允许匿名访问
            // .anonymous()： 允许匿名访问，如果携带了token，则不允许访问
            // .permitAll()： 允许所有访问，如果携带了token，也允许访问
            .antMatchers("/auth/oauth/token").anonymous()
            // 其他接口都需要鉴权认证
            .anyRequest().authenticated();

        //配置异常处理器
        // authenticationEntryPoint是系统自定义的认证失败处理实现类
        // 因为有了@Configuration注解，所以可以注入该类
        // SpringSecurity内置的异常过滤器调用的是异常处理器接口的实现类，所以我们需要在系统自定义Security的异常实现类
        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)  // 配置认证失败处理器
                .accessDeniedHandler(accessDeniedHandler);   // 授权失败处理器

        // 关闭默认的注销功能
        http.logout().disable();

        // 在UsernamePasswordAuthenticationFilter过滤器的前面添加jwt过滤器
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // 允许跨域
        http.cors();

        return http.build();
    }

    // 因为有用到具体的adminDetailsService，所以不把该配置类写在common模块里
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        return http
                .getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(adminDetailsService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
    }

    /**
     * @Bean: 向spring容器中注册BCryptPasswordEncoder（在其他地方可直接注入）
     * 用来替换Security默认的密码加密方式
     **/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
