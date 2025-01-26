package com.zhw.blog.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhw.blog.common.result.ResponseResult;
import com.zhw.blog.model.entity.User;
import com.zhw.blog.model.vo.login.UserLoginVo;

import java.util.HashMap;

/**
 * @author zhanghuaiwei
 * @date 2024/12/27 10:33
 */
public interface AuthService extends IService<User> {
    HashMap<String, Object> authToken(UserLoginVo userLoginVo);

    ResponseResult logout();
}
