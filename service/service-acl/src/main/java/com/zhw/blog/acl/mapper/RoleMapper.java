package com.zhw.blog.acl.mapper;

import com.zhw.blog.model.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author ZN220203
* @description 针对表【t_sys_role(系统角色表)】的数据库操作Mapper
* @createDate 2024-12-31 10:31:34
* @Entity com.zhw.blog.model.entity.Role
*/
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

}




