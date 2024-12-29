package com.zhw.blog.web.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhw.blog.common.config.JwtConfig;
import com.zhw.blog.common.constant.RedisConstant;
import com.zhw.blog.common.exception.BlogException;
import com.zhw.blog.common.result.ResponseResult;
import com.zhw.blog.common.result.ResultCodeEnum;
import com.zhw.blog.common.security.AdminLoginSecurity;
import com.zhw.blog.common.util.RedisCacheUtil;
import com.zhw.blog.common.util.SecurityUtils;
import com.zhw.blog.model.entity.Admin;
import com.zhw.blog.web.admin.mapper.AuthMapper;
import com.zhw.blog.web.admin.service.AuthService;
import com.zhw.blog.web.admin.vo.login.AdminLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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
public class AuthServiceImpl extends ServiceImpl<AuthMapper, Admin>
        implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtConfig jwtConfig;
    @Autowired
    private RedisCacheUtil redisCacheUtil;




    @Override
    public HashMap<String, Object> authToken(AdminLoginVo adminLoginVo) {
        // 使用SpringSecurity的AuthenticationManager 进行身份认证
        // authenticationManager的认证方法authenticate，接收一个Authentication类型的参数
        // Authentication：是接口类型
        // 所以这块使用Authentication他的实现类UsernamePasswordAuthenticationToken
        // alt+ctrl+点击Authentication：可查看实现类
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(adminLoginVo.getUsername(),adminLoginVo.getPassword());
        // 把客户端传的用户名和密码封装成authenticationToken
        // 通过authenticationManager调用authenticate
        // 可执行在自定义的AdminDetailsServiceImpl重写的loadUserByUsername方法
        // 从而实现用户的登录认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // authenticate为空：认证失败
        if(Objects.isNull(authenticate)) {
            throw new BlogException(ResultCodeEnum.LOGIN_ERROR);
        }
        // 认证成功后，在authenticate解析用户信息
        // getPrincipal() 可以拿到对应数据库表的整条数据记录
        // 强转成UserDetails的实现类AdminLoginSecurity
        AdminLoginSecurity adminLoginSecurity = (AdminLoginSecurity) authenticate.getPrincipal();
        // AdminLoginSecurity adminLoginSecurity = SecurityUtils.getLoginAdmin();
        // 获取登录id,即是登录username/phone，唯一的
        String loginId = adminLoginSecurity.getUsername();
        // 生成token
        String token = jwtConfig.createJWT(loginId);
        // 将token存储到redis中
        String key = RedisConstant.ADMIN_LOGIN_PREFIX + loginId;
        redisCacheUtil.setCacheObject(key, token, RedisConstant.ADMIN_LOGIN_TOKEN_TTL_SEC, TimeUnit.SECONDS);
        // 组装token，返回给前端
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("accessToken", token);
        return resultMap;
    }

    @Override
    public ResponseResult logout() {
        // 获取保存在SecurityContextHolder的登录id
        String loginId = SecurityUtils.getAdminLoginId();
        // 删除redis对应的token
        redisCacheUtil.deleteObject(RedisConstant.ADMIN_LOGIN_PREFIX + loginId);
        return ResponseResult.okResult();
    }
}
