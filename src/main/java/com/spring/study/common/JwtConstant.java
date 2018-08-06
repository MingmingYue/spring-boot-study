package com.spring.study.common;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
public final class JwtConstant {


    public static final String TOKEN = "Token";
    /**
     * 请求携带参数
     */
    public static final String BEARER = "Bearer";
    /**
     * 秘钥
     */
    public static final String JWT_SECRET = "spring-security-@Jwt!&Secret^#";
    /**
     * 有效期
     */
    public static final long JWT_TTL = 2 * 60 * 60 * 1000;
}
