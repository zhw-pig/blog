package com.zhw.blog.web.admin;

import com.zhw.blog.web.admin.mapper.MenuMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author zhanghuaiwei
 * @date 2024/12/31 11:13
 */
@SpringBootTest
public class TestPerms {


    @Autowired
    private MenuMapper menuMapper;

    @Test
    public void testSelectPerms() {
        List<String> permissions = menuMapper.selectPermissionByUserId("3b4f3f8ce018f45178d0bf7eb661dcc9");
        System.out.println(permissions);
    }
}
