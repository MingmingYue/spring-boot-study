package com.spring.study.web;

import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/6/30.
 */
@Configuration
public class WebContextFilter implements Filter {

    private final static String OPTIONS = "OPTIONS";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (request.getMethod().equalsIgnoreCase(OPTIONS)) {
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
