package com.zhw.blog.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhw.blog.model.entity.Role;
import com.zhw.blog.web.admin.service.RoleService;
import com.zhw.blog.web.admin.mapper.RoleMapper;
import org.springframework.stereotype.Service;

/**
* @author ZN220203
* @description 针对表【t_sys_role(系统角色表)】的数据库操作Service实现
* @createDate 2024-12-31 10:31:34
*/
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role>
    implements RoleService{

}




