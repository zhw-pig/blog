package com.zhw.blog.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhw.blog.acl.mapper.AuthMapper;
import com.zhw.blog.acl.service.AuthService;
import com.zhw.blog.common.config.JwtConfig;
import com.zhw.blog.common.constant.RedisConstant;
import com.zhw.blog.common.exception.BlogException;
import com.zhw.blog.common.result.ResponseResult;
import com.zhw.blog.common.result.ResultCodeEnum;
import com.zhw.blog.common.security.UserLoginSecurity;
import com.zhw.blog.common.utils.RedisCacheUtil;
import com.zhw.blog.common.utils.SecurityUtils;
import com.zhw.blog.model.entity.User;
import com.zhw.blog.model.vo.login.UserLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author zhanghuaiwei
 * @date 2024/12/27 10:34
 */
@Service
public class AuthServiceImpl extends ServiceImpl<AuthMapper, User>
        implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private RedisCacheUtil redisCacheUtil;
    @Autowired
    private SecurityUtils securityUtils;




    @Override
    public HashMap<String, Object> authToken(UserLoginVo userLoginVo) {

        Authentication authenticate = securityUtils.getAuthentication(userLoginVo.getUsername(), userLoginVo.getPassword());

        // authenticate为空：认证失败
        if(Objects.isNull(authenticate)) {
            throw new BlogException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 认证成功后，在authenticate解析用户信息
        // getPrincipal() 可以拿到对应数据库表的整条数据记录
        // 强转成UserDetails的实现类AdminLoginSecurity
        UserLoginSecurity userLoginSecurity = (UserLoginSecurity) authenticate.getPrincipal();
        // UserLoginSecurity userLoginSecurity = SecurityUtils.getLoginAdmin();
        // 获取登录id,即是登录username/phone，唯一的
        String loginId = userLoginSecurity.getUsername();
        // 生成token
        String token = jwtConfig.createJWT(loginId);
        // 将token存储到redis中
        String key = RedisConstant.ADMIN_LOGIN_PREFIX + loginId;
        redisCacheUtil.setCacheObject(key, userLoginSecurity, RedisConstant.ADMIN_LOGIN_TOKEN_TTL_SEC, TimeUnit.SECONDS);
        // 组装token，返回给前端
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("accessToken", token);
        return resultMap;
    }

    @Override
    public ResponseResult logout() {
        // 获取保存在SecurityContextHolder的登录id
        String loginId = SecurityUtils.getAdminUsername();
        // 删除redis对应的token
        redisCacheUtil.deleteObject(RedisConstant.ADMIN_LOGIN_PREFIX + loginId);
        return ResponseResult.okResult();
    }
}
