package com.zhw.blog.common.security;

import com.zhw.blog.model.entity.User;
import com.zhw.blog.model.enums.BaseStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhanghuaiwei
 * @date 2024/12/29 11:11
 * @description 通过实现并重写UserDetails，应用于重写的loadUserByUsername返回
 */
@Data
@NoArgsConstructor
public class AdminLoginSecurity implements UserDetails {

    private User admin;

    private List<String> permissions;

    // 定义成 成员变量，避免框架每次执行getAuthorities的时候，去封装authorities
    // 避免存储到redis
    // @JSONField(serialize = false)  // fastjson提供的注解
    private Set<GrantedAuthority> authorities;

    public AdminLoginSecurity(User admin, List<String> permissions) {
        this.admin = admin;
        this.permissions = permissions;
    }

    // 获取权限信息
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(authorities != null) {
            return authorities;
        }
        // 把permissions中的String类型的权限信息封装成SimpleGrantedAuthority
        Set<GrantedAuthority> authorities = permissions
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        return authorities;
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getLoginId();
    }

    @Override
    public boolean isEnabled() {
        return admin.getState() == BaseStatus.ENABLE;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }


}
