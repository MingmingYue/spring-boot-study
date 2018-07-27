package com.spring.study.security;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/6/30.
 */
public interface TokenManager {

    String createToken(String mobile, int id);

    boolean checkToken(String token);
}
