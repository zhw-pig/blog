package com.zhw.blog.common.config;



import com.zhw.blog.common.exception.BlogException;
import com.zhw.blog.common.result.ResultCodeEnum;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 * 将这个工具可以设置为配置类   将配置信息写入yml中
 */

@SuppressWarnings("all")
@Configuration
public class JwtConfig {

    @Autowired
    // 这里不能加static， 如果加了static  则获取不到jwtConfig

    private JwtPropertyConfig jwtPropertyConfig;


    public  String getUUID(){
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;
    }
    
    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     * @return
     */
    public String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());// 设置过期时间
        return builder.compact();
    }

    /**
     * 生成jtw
     * @param subject token中要存放的数据（json格式）
     * @param ttlMillis token超时时间
     * @return
     */
    public String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());// 设置过期时间
        return builder.compact();
    }

    private JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if(ttlMillis==null){
            ttlMillis=jwtPropertyConfig.getExpiration();
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        return Jwts.builder()
                .setId(uuid)              //唯一的ID
                .setSubject(subject)   // 主题  可以是JSON数据
                .setIssuer(jwtPropertyConfig.getIssuer())     // 签发者
                .setIssuedAt(now)      // 签发时间
                .signWith(signatureAlgorithm, secretKey) //使用HS256对称加密算法签名, 第二个参数为秘钥
                .setExpiration(expDate);
    }

    /**
     * 创建token
     * @param id
     * @param subject
     * @param ttlMillis
     * @return
     */
    public  String createJWT(String id, String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, id);// 设置过期时间
        return builder.compact();
    }


    /**
     * 生成加密后的秘钥 secretKey
     * @return
     */
    public SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(jwtPropertyConfig.getSecret());
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, jwtPropertyConfig.getAlgorithm());
        return key;
    }
    
    /**
     * 解析
     * 为所有受保护的接口增加校验JWT合法性的逻辑
     * @param jwt
     * @return
     * @throws Exception
     */
    public Claims parseToken(String token) throws Exception {
        // 令牌为空
        // 返回未登录异常
        if (token == null || "".equals(token)) {
            throw new BlogException(ResultCodeEnum.NO_LOGIN);
        }

        try{
            SecretKey secretKey = generalKey();
            JwtParser jwtParser = Jwts.parser().setSigningKey(secretKey);
            return jwtParser.parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e){
            throw new BlogException(ResultCodeEnum.TOKEN_EXPIRED);
        } catch (JwtException e){
            throw new BlogException(ResultCodeEnum.TOKEN_INVALID);
        }
    }
}