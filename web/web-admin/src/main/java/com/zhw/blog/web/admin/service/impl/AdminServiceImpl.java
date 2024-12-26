package com.zhw.blog.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhw.blog.model.entity.Admin;
import com.zhw.blog.web.admin.mapper.AdminMapper;
import com.zhw.blog.web.admin.service.AdminService;
import org.springframework.stereotype.Service;

/**
* @author zhw
* @description 针对表【t_sys_admin】的数据库操作Service实现
* @createDate 2024-12-26 11:57:37
*/
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
        implements AdminService {

}