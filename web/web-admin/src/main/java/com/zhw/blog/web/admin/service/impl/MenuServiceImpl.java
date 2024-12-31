package com.zhw.blog.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhw.blog.model.entity.Menu;
import com.zhw.blog.web.admin.mapper.MenuMapper;
import com.zhw.blog.web.admin.service.MenuService;
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
