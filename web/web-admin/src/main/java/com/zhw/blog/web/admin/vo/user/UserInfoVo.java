package com.zhw.blog.web.admin.vo.user;

import com.zhw.blog.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * @author zhanghuaiwei
 * @date 2024/12/30 10:24
 * 后台登录用户详情
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVo {
    private List<String> permissions;
    private List<String> roles;
    private User user;
}
