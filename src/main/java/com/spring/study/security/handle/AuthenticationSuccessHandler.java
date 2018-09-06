package com.spring.study.security.handle;

import com.google.gson.Gson;
import com.spring.study.common.JwtConstant;
import com.spring.study.common.TimeUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 登录成功处理类
 *
 * @author: ZhouMingming
 * @data: Create on 2018/9/6.
 */
@Component
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        List<GrantedAuthority> list = (List<GrantedAuthority>) ((UserDetails) authentication.getPrincipal()).getAuthorities();
        List<String> authorities = list.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        String token = JwtConstant.BEARER + Jwts.builder()
                .setSubject(username)
                .claim(JwtConstant.AUTHORITIES, new Gson().toJson(authorities))
                .setExpiration(new Date(TimeUtils.getCurr() + JwtConstant.JWT_TTL))
                .signWith(SignatureAlgorithm.HS512, JwtConstant.JWT_SECRET)
                .compact();
        response.addHeader(JwtConstant.TOKEN, JwtConstant.BEARER + token);
    }
}
