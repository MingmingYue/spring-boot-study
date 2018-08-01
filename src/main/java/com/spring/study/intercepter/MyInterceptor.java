package com.spring.study.intercepter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 多个拦截器的运行顺序按照定义顺序执行
 *
 * @author: ZhouMingming
 * @data: Create on 2018/8/1.
 */
@Slf4j
public class MyInterceptor extends HandlerInterceptorAdapter {

    /**
     * controller 执行之后，且页面渲染之前调用
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        log.info("进入 preHandle 方法... {} ,{} ", httpServletRequest.getRequestURL().toString(), httpServletRequest.getRequestURI());
        return true;
    }

    /**
     * controller 执行之后，且页面渲染之前调用
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("进入 postHandle 方法... {} ,{} ", httpServletRequest.getRequestURL().toString(), httpServletRequest.getRequestURI());
    }

    /**
     * 页面渲染之后调用，一般用于资源清理操作
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("进入 afterCompletion 方法... {} ,{} ", httpServletRequest.getRequestURL().toString(), httpServletRequest.getRequestURI());

    }
}
