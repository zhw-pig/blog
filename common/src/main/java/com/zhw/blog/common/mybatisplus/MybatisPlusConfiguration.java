package com.zhw.blog.common.mybatisplus;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhanghuaiwei
 * @date 2024/11/14 9:54
 * 确保在 MyBatis Plus 配置中注册了枚举类型转换器
 */
@Configuration
// @MapperScan扫描并注册项目中的mapper包下的所有Mapper类
// @MapperScan只是会被Spring解析，不会被IDEA解析，
// 所以注入Mapper的时候会报Cont find bean警告，但不影响使用
// 避免出现警告可以在Mapper类上，单独加上@Mapper/@Repository注解
@MapperScan("com.zhw.blog.web.*.mapper")
public class MybatisPlusConfiguration {
    @Bean
    // 配置Mybatis-Plus分页插件
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new
                MybatisPlusInterceptor();
        // mybatis plus的分页插件
        mybatisPlusInterceptor.addInnerInterceptor(new
                PaginationInnerInterceptor(DbType.MYSQL));
        // mybatis plus的乐观锁插件
        // mybatisPlusInterceptor.addInnerInterceptor(new
        //         OptimisticLockerInnerInterceptor());
        // 防止全局修改和删除
        // mybatisPlusInterceptor.addInnerInterceptor(new
        //         BlockAttackInnerInterceptor());
        return mybatisPlusInterceptor;
    }


}