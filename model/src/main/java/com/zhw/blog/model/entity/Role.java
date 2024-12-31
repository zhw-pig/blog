package com.zhw.blog.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhw.blog.model.enums.BaseStatus;
import com.zhw.blog.model.enums.RoleClassifyType;
import com.zhw.blog.model.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统角色表
 * @TableName t_sys_role
 */
@TableName(value ="t_sys_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity {

    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;

    /**
     * 角色编码
     */
    @TableField("role_code")
    private String roleCode;

    /**
     * 角色描述
     */
    @TableField("role_desc")
    private String roleDesc;

    /**
     * 角色类型，0角色，1分组
     */
    @TableField("role_type")
    private RoleType roleType;

    /**
     * 开启标识 （0为禁用，1为启用）
     */
    @TableField("state")
    private BaseStatus state;

    /**
     * 角色分组类型（只有自定义分组才可编辑）
     * 0：博客管理角色
     * 1：博客pc客户端角色
     * 2：博客app客户端角色
     * 3：博客小程序客户端角色
     * 4：自定义角色
     */
    @TableField("classify")
    private RoleClassifyType classify;

    /**
     * 排序值
     */
    @TableField("sort")
    private Integer sort;


    /**
     * 父节点id
     */
    @TableField("parent_id")
    private String parentId;

}