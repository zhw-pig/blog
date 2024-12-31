package com.zhw.blog.web.admin.controller.user;

import com.zhw.blog.common.result.ResponseResult;
import com.zhw.blog.common.util.SecurityUtils;
import com.zhw.blog.model.entity.User;
import com.zhw.blog.web.admin.service.UserService;
import com.zhw.blog.web.admin.vo.user.UserInfoVo;
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
@Tag(name = "User相关")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("selectUserByToken")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")  // 设置接口访问权限 hasAuthority('USER')
    public ResponseResult<UserInfoVo> selectUserByToken() {
        // 获取当前登录的用户账号
        String username = SecurityUtils.getAdminUsername();
        // 根据用户id查询权限信息
        // List<String> perms = null;
        // 根据用户id查询角色信息
        // List<String> roleKeyList = null;
        // 获取用户账号获取用户信息
        User admin = userService.getAdminByUsername(username);
        UserInfoVo userInfoVo = new UserInfoVo(null, null, admin);
        // 封装数据返回
        return ResponseResult.okResult(userInfoVo);
    }
}
