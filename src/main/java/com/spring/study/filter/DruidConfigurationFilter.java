package com.spring.study.filter;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * druid监控
 *
 * @author: ZhouMingming
 * @data: Create on 2018/8/8.
 */
@Configuration
public class DruidConfigurationFilter {

    @Bean
    @SuppressWarnings({"unchecked"})
    public ServletRegistrationBean registrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid1/*");
        bean.addInitParameter("allow", "127.0.0.1");
        //IP黑名单 (存在共同时，deny优先于allow)
        bean.addInitParameter("deny", "192.168.1.73");
        //登录查看信息的账号密码.
        bean.addInitParameter("loginUsername", "root");
        bean.addInitParameter("loginPassword", "root");
        //是否能够重置数据.
        bean.addInitParameter("resetEnable", "false");
        return bean;
    }

    @Bean
    @SuppressWarnings({"unchecked"})
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        bean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        bean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid2/*");
        return bean;
    }
}
