package com.zhw.blog.model.vo.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @author zhanghuaiwei
 * @date 2024/12/27 16:01
 */
@Data
@Schema(description = "返回给前端的token结构")
public class TokenVo {


    private String accessToken;

    private Integer expiresIn;

    private String jti;

    private String refreshToken;

    private String tokenType;

    private String scope;
}
