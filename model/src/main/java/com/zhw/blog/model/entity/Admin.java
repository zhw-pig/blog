package com.zhw.blog.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhw.blog.model.enums.AdminType;
import com.zhw.blog.model.enums.BaseStatus;
import lombok.Data;

/**
 * @TableName t_sys_admin
 */
@TableName(value ="t_sys_admin")
@Data
public class Admin extends BaseEntity {
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 登陆账号
     */
    @TableField("login_id")
    private String loginId;
    /**
     * 管理员姓名
     */
    @TableField("name")
    private String name;
    /**
     * 管理员密码
     */
    @JsonIgnore
    @TableField("password")
    private String password;
    /**
     * 手机号
     */
    @TableField("phone")
    private String phone;
    /**
     * 头像【存放图片信息】
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 是否为超级管理员     1:是   0:否 
     */
    @TableField("is_admin")
    private AdminType isAdmin;
    /**
     * 1-启动，0-禁用
     */
    @TableField("state")
    private BaseStatus state;

    /**
     * 记录创建者ID
     */
    @TableField("create_by")
    private String createBy;

    /**
     * 最后修改人ID
     */
    @TableField("last_modified_by")
    private String lastModifiedBy;

    /**
     * 创建人名称
     */
    @TableField("create_name")
    private String createName;

    /**
     * 最后修改人名称
     */
    @TableField("last_modified_name")
    private String lastModifiedName;

}