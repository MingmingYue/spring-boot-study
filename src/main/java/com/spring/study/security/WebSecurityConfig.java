package com.spring.study.security;

import com.spring.study.config.IgnoredUrlsProperties;
import com.spring.study.security.impl.CustomAuthenticationProvider;
import com.spring.study.filter.JwtAuthenticationFilter;
import com.spring.study.filter.JwtLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/8/4.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private IgnoredUrlsProperties ignoredUrlsProperties;

    public WebSecurityConfig(UserDetailsService userDetailsService,
                             BCryptPasswordEncoder bCryptPasswordEncoder,
                             IgnoredUrlsProperties ignoredUrlsProperties) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.ignoredUrlsProperties = ignoredUrlsProperties;
    }

    /**
     * 使用自定义身份验证组件
     */
    @Override
    public void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.authenticationProvider(new CustomAuthenticationProvider(userDetailsService, bCryptPasswordEncoder));
    }

    /**
     * 设置http 验证规则
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        System.out.println(ignoredUrlsProperties.getUrls().toString());
        httpSecurity
                .cors().and() // 跨域支持
                .csrf().disable()
                // jwt 不需要session 所以不需要创建会话
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(ignoredUrlsProperties.getUrls().toString()).permitAll()
                .anyRequest().authenticated() // 所有请求需要身份验证
                .and()
                .addFilter(new JwtLoginFilter(authenticationManager()))
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/user/login")
                .permitAll() // 设置注销成功后的跳转到登录页面
                .disable()
                .headers().cacheControl(); // 禁用页面缓存
    }
}
