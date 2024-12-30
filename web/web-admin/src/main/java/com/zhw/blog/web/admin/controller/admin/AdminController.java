package com.zhw.blog.web.admin.controller.admin;

import com.zhw.blog.common.result.ResponseResult;
import com.zhw.blog.common.util.SecurityUtils;
import com.zhw.blog.model.entity.User;
import com.zhw.blog.web.admin.service.AdminService;
import com.zhw.blog.web.admin.vo.admin.AdminInfoVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @Autowired
    private AdminService adminService;


    @GetMapping("selectUserByToken")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")  // 设置接口访问权限 hasAuthority('USER')
    public ResponseResult<AdminInfoVo> selectUserByToken() {
        // 获取当前登录的用户账号
        String username = SecurityUtils.getAdminUsername();
        // 根据用户id查询权限信息
        // List<String> perms = null;
        // 根据用户id查询角色信息
        // List<String> roleKeyList = null;
        // 获取用户账号获取用户信息
        User admin = adminService.getAdminByUsername(username);
        AdminInfoVo adminInfoVo = new AdminInfoVo(null, null, admin);
        // 封装数据返回
        return ResponseResult.okResult(adminInfoVo);
    }
}
