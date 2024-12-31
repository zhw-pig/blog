package com.zhw.blog.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zhw.blog.model.enums.AdminType;
import com.zhw.blog.model.enums.BaseStatus;
import com.zhw.blog.model.enums.UserSourceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName t_sys_user
 */
@TableName(value ="t_sys_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends BaseEntity {


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
     * 登录用户来源 0：后台管理员；1：客户端用户
     */
    @TableField("source")
    private UserSourceType source;

}