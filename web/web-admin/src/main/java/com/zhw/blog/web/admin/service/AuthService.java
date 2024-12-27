package com.zhw.blog.web.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhw.blog.model.entity.Admin;
import com.zhw.blog.web.admin.vo.login.AdminLoginVo;

/**
 * @author zhanghuaiwei
 * @date 2024/12/27 10:33
 */
public interface AuthService extends IService<Admin> {
    String authToken(AdminLoginVo adminLoginVo);
}
