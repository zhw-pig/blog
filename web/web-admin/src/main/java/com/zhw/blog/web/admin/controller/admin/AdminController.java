package com.zhw.blog.web.admin.controller.admin;

import com.zhw.blog.common.result.ResponseResult;
import com.zhw.blog.model.entity.Admin;
import com.zhw.blog.web.admin.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhanghuaiwei
 * @date 2024/12/26 13:42
 */
@Tag(name = "Admin相关")
@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Operation(summary = "后台登录")
    @GetMapping("login")
    public ResponseResult<List<Admin>> login() {
        List<Admin> list = adminService.list();
        return ResponseResult.ok(list);
    }
}
