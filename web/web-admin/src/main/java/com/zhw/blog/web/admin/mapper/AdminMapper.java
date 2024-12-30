package com.zhw.blog.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhw.blog.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author zhw
* @description 针对表【t_sys_admin】的数据库操作Mapper
* @createDate 2024-12-26 11:57:37
*/
@Mapper
public interface AdminMapper extends BaseMapper<User> {

}