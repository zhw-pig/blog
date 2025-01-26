package com.zhw.blog.acl.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhw.blog.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhanghuaiwei
 * @date 2024/12/27 10:35
 */
@Mapper
public interface AuthMapper extends BaseMapper<User> {
}
