package com.spring.study.web;

import com.spring.study.security.SecurityAspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 多个过滤器可以使用order注解来配置执行顺序
 *
 * @author: ZhouMingming
 * @data: Create on 2018/6/30.
 */
@Configuration
public class WebContextFilter extends OncePerRequestFilter {

    private final static String OPTIONS = "OPTIONS";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = httpServletRequest;
        HttpServletResponse response = httpServletResponse;

        if (request.getMethod().equalsIgnoreCase(OPTIONS) && WebContext.getRequest().getHeader(SecurityAspect.DEFAULT_TOKEN_NAME) == null) {
            return;
        }

        WebContext.init(request, response);
        try {
            filterChain.doFilter(request, response);
        } finally {
            WebContext.destroy();
        }
    }

    @Override
    public void destroy() {
    }
}
