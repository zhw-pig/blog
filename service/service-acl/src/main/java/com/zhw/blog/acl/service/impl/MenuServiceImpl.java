package com.zhw.blog.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhw.blog.acl.service.MenuService;
import com.zhw.blog.model.entity.Menu;
import com.zhw.blog.acl.mapper.MenuMapper;
import org.springframework.stereotype.Service;

/**
 * 系统权限菜单表(Menu)表服务实现类
 *
 * @author zhanghuaiwei
 * @since 2024-12-31 09:58:23
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {
}
