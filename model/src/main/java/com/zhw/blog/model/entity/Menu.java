package com.zhw.blog.model.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.zhw.blog.model.enums.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * 系统权限菜单表(Menu)表实体类
 *
 * @author zhanghuaiwei
 * @since 2024-12-31 09:58:21
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_sys_menu")
public class Menu extends BaseEntity {

    //父菜单ID
    @TableField("pid")
    private String pid;
    //菜单名称
    @TableField("name")
    private String name;
    //菜单权限标识
    @TableField("permission")
    private String permission;
    //前端路由标识路径，第三方资源完整路径
    @TableField("path")
    private String path;
    //前端组件URL
    @TableField("cmp_path")
    private String cmpPath;
    //图标
    @TableField("icons")
    private String icons;

    //排序值
    @TableField("sort")
    private Integer sort;
    // -1: 菜单 0: 菜单分组 1: 按钮 2：虚拟目录 3：外部资源
    @TableField("type")
    private MenuType type;

    //资源标识，区别是本地资源，还是外部资源 （0代表本地；1代表外部）
    @TableField("source")
    private MenuSourceType source;
    //分组：0、业务资源菜单；1、系统资源菜单
    @TableField("classify")
    private MenuClassifyType classify;
    //是否隐藏路由0为隐藏；1为显示
    @TableField("hidden")
    private HiddenType hidden;
    //数据状态0为禁用，1为启用
    @TableField("state")
    private BaseStatus state;
    //描述
    @TableField("description")
    private String description;
    // 是否上锁: 0未上锁，1上锁
    @TableField("is_lock")
    private LockType isLock;
}
