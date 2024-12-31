package com.zhw.blog.web.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhw.blog.model.entity.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统权限菜单表(Menu)表数据库访问层
 *
 * @author zhanghuaiwei
 * @since 2024-12-31 09:58:21
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    // 根据用户id查询权限信息
    List<String> selectPermissionByUserId(String userId);
}
