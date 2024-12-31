package com.zhw.blog.web.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhw.blog.model.entity.User;

public interface UserService extends IService<User> {

    User getAdminByUsername(String username);
}