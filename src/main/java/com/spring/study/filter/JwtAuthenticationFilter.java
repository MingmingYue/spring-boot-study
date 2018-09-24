package com.spring.study.filter;

import cn.hutool.core.util.StrUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.spring.study.common.JwtConstant;
import com.spring.study.exception.TokenException;
import io.jsonwebtoken.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/8/3.
 */
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

    private static final Logger log = LogManager.getLogger(JwtAuthenticationFilter.class);

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JwtConstant.TOKEN);
        if (header == null || !header.startsWith(JwtConstant.BEARER)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(request);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        chain.doFilter(request, response);
    }


    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JwtConstant.TOKEN);
        if (StrUtil.isNotBlank(token)) {
            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(JwtConstant.JWT_SECRET)
                        .parseClaimsJws(token.replace(JwtConstant.BEARER, ""))
                        .getBody();

                String username = claims.getSubject();
                if (StrUtil.isNotBlank(username)) {
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    String authority = claims.get(JwtConstant.AUTHORITIES).toString();

                    if (StrUtil.isNotBlank(authority)) {
                        List<String> list = new Gson().fromJson(authority, new TypeToken<List<String>>() {}.getType());
                        list.stream().forEach(s -> authorities.add(new SimpleGrantedAuthority(s)));
                    }
                    User principal = new User(username, "", authorities);
                    return new UsernamePasswordAuthenticationToken(principal, null, authorities);
                }
            } catch (ExpiredJwtException e) {
                logger.error("Token已过期: {} " + e);
                throw new TokenException("Token已过期");
            } catch (UnsupportedJwtException e) {
                logger.error("Token格式错误: {} " + e);
                throw new TokenException("Token格式错误");
            } catch (MalformedJwtException e) {
                logger.error("Token没有被正确构造: {} " + e);
                throw new TokenException("Token没有被正确构造");
            } catch (SignatureException e) {
                logger.error("签名失败: {} " + e);
                throw new TokenException("签名失败");
            } catch (IllegalArgumentException e) {
                logger.error("非法参数异常: {} " + e);
                throw new TokenException("非法参数异常");
            }
        }
        return null;
    }

}
