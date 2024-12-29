package com.zhw.blog.web.admin.controller.admin;

import com.zhw.blog.common.result.ResponseResult;
import com.zhw.blog.model.entity.Admin;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author zhanghuaiwei
 * @date 2024/12/26 13:42
 */
@Tag(name = "Admin相关")
@RestController
@RequestMapping("admin")
public class AdminController {



    @GetMapping("selectUserByToken")
    public ResponseResult<Admin> selectUserByToken() {
        return ResponseResult.okResult();
    }
}
