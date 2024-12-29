package com.zhw.blog.web.admin.controller.auth;

import com.zhw.blog.common.constant.RedisConstant;
import com.zhw.blog.common.result.ResponseResult;
import com.zhw.blog.web.admin.service.AuthService;
import com.zhw.blog.web.admin.vo.login.AdminLoginVo;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;

/**
 * @author zhanghuaiwei
 * @date 2024/12/27 10:19
 */
@Tag(name = "权限相关")
@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    // consumes = "application/x-www-form-urlencoded;charset=UTF-8"
    @PostMapping(value = "oauth/token")
    public ResponseResult<HashMap<String, Object>> authToken(@RequestBody AdminLoginVo adminLoginVo) {
        HashMap<String, Object> tokenMap = authService.authToken(adminLoginVo);
        return ResponseResult.okResult(tokenMap);
    }

    @PostMapping("oauth/logout")
    public ResponseResult logout() {
        return authService.logout();
    }
}
