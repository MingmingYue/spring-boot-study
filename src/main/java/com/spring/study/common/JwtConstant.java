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
    public static final String BEARER = "Bearer ";
    /**
     * 秘钥
     */
    public static final String JWT_SECRET = "d4624c36b6795d1d99dcf0547af5443d";
    /**
     * 有效期
     */
    public static final Long JWT_TTL = 2000 * 60 * 60 * 1000L;
    /**
     * 权限参数头
     */
    public static final String AUTHORITIES = "authorities";
}
