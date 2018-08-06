package com.spring.study.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.study.common.JwtConstant;
import com.spring.study.common.TimeUtils;
import com.spring.study.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * @author: ZhouMingming
 * @data: Create on 2018/8/3.
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;


    public JwtLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * 接收并解析用户凭证
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            new ArrayList<>(10))
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 生成token
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token;
        try {
            Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
            List roleList = new ArrayList<>(10);
            for (GrantedAuthority grantedAuthority : authorities) {
                roleList.add(grantedAuthority.getAuthority());
            }
            token = Jwts.builder()
                    .setSubject(authResult.getName() + "-" + roleList)
                    .setExpiration(new Date(TimeUtils.getCurr() + JwtConstant.JWT_TTL))
                    .signWith(SignatureAlgorithm.HS256, JwtConstant.JWT_SECRET)
                    .compact();
            response.addHeader(JwtConstant.TOKEN, JwtConstant.BEARER + token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
