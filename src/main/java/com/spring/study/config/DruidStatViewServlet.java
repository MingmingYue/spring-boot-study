package com.spring.study.config;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
@WebServlet(urlPatterns = "/druid/*", initParams = {@WebInitParam(name = "allow", value = "192.168.1.72,127.0.0.1"), // IP白名单 默认所有可以访问
        @WebInitParam(name = "deny", value = "192.168.1.73"), // IP黑名单 (存在共同时，deny优先于allow)
        @WebInitParam(name = "loginUsername", value = "root"),
        @WebInitParam(name = "loginPassword", value = "root"),
        @WebInitParam(name = "resetEnable", value = "false")// 禁用HTML页面上的“Reset All”功能
})
public class DruidStatViewServlet extends StatViewServlet {

    private static final long serialVersionUID = 1776463055477495407L;

}