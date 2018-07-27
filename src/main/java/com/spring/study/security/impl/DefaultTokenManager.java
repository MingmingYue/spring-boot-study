package com.spring.study.security.impl;

import com.spring.study.common.JwtConstant;
import com.spring.study.common.JwtUtils;
import com.spring.study.security.TokenManager;
import org.springframework.stereotype.Service;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/6/30.
 */
@Service("TokenManager")
public class DefaultTokenManager implements TokenManager {

    @Override
    public String createToken(String mobile, int id) {
        return JwtUtils.createJWT(String.valueOf(id), mobile, JwtConstant.JWT_TTL);
    }

    @Override
    public boolean checkToken(String token) {
        return JwtUtils.validateJWT(token).isSuccess();
    }
}
