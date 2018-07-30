package com.spring.study.web;


import com.spring.study.entity.Permission;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/6/30.
 */
public class WebContext {

    private static ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();
    private static ThreadLocal<HttpServletResponse> responseHolder = new ThreadLocal<>();
    public static ThreadLocal<Permission> userHolder = new ThreadLocal<>();

    public static void init(HttpServletRequest request, HttpServletResponse response) {
        requestHolder.set(request);
        responseHolder.set(response);
    }

    public static void destroy() {
        requestHolder.remove();
        responseHolder.remove();
        userHolder.remove();
    }

    public static HttpServletRequest getRequest() {
        return requestHolder.get();
    }

    public static HttpServletResponse getResponse() {
        return responseHolder.get();
    }
}
