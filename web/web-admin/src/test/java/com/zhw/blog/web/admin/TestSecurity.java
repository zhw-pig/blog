package com.zhw.blog.web.admin;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;


/**
 * @author zhanghuaiwei
 * @date 2024/12/29 13:13
 */
@SpringBootTest
public class TestSecurity {

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void testBCrypt() {
        final String encode = bCryptPasswordEncoder.encode("123456");
        String enPwd = "$2a$10$AszfcvqfTFVtgt2GsM9OwemUGrikzdd1rP3iZmxbh0N1m8eFznIiq";
        // System.out.println(encode);
        System.out.println(bCryptPasswordEncoder.matches("123456", enPwd));
    }
}
