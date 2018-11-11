package com.spring.study.filter;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * druid监控
 *
 * @author: ZhouMingming
 * @data: Create on 2018/8/8.
 */
@Configuration
public class DruidConfigurationFilter {

    @Value("spring-boot.druid.allow")
    private String ALLOW_PARAMETER;
    @Value("spring-boot.druid.deny")
    private String DENY_PARAMETER;
    @Value("spring-boot.druid.login-username")
    private String LOGIN_USERNAME;
    @Value("spring-boot.druid.login-password")
    private String LOGIN_PASSWORD;

    @Bean
    @SuppressWarnings({"unchecked"})
    public ServletRegistrationBean registrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid1/*");
        bean.addInitParameter("allow", ALLOW_PARAMETER);
        //IP黑名单 (存在共同时，deny优先于allow)
        bean.addInitParameter("deny", DENY_PARAMETER);
        //登录查看信息的账号密码.
        bean.addInitParameter("loginUsername", LOGIN_USERNAME);
        bean.addInitParameter("loginPassword", LOGIN_PASSWORD);
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
