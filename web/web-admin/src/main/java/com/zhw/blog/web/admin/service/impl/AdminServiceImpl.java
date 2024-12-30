package com.zhw.blog.web.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhw.blog.model.entity.User;
import com.zhw.blog.web.admin.mapper.AdminMapper;
import com.zhw.blog.web.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
* @author zhw
* @description 针对表【t_sys_user】的数据库操作Service实现
* @createDate 2024-12-26 11:57:37
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, User>
        implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public User getAdminByUsername(String username) {
        LambdaQueryWrapper<User> adminQueryWrapper = new LambdaQueryWrapper<>();
        adminQueryWrapper.eq(StringUtils.hasText(username), User::getLoginId, username);
        User admin = adminMapper.selectOne(adminQueryWrapper);
        return admin;
    }
}