package com.spring.study.security;

import com.spring.study.config.IgnoredUrlsProperties;
import com.spring.study.filter.JwtAuthenticationFilter;
import com.spring.study.security.handle.AuthenticationSuccessHandler;
import com.spring.study.security.permission.MyFilterSecurityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/8/4.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsService userDetailsService;
    private IgnoredUrlsProperties ignoredUrlsProperties;
    private MyFilterSecurityInterceptor myFilterSecurityInterceptor;
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService,
                             IgnoredUrlsProperties ignoredUrlsProperties,
                             MyFilterSecurityInterceptor myFilterSecurityInterceptor,
                             AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.ignoredUrlsProperties = ignoredUrlsProperties;
        this.myFilterSecurityInterceptor = myFilterSecurityInterceptor;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    /**
     * 使用自定义身份验证组件
     */
    @Override
    public void configure(AuthenticationManagerBuilder managerBuilder) throws Exception {
        managerBuilder.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    /**
     * 设置http 验证规则
     */
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity.authorizeRequests();
        ignoredUrlsProperties.getUrls().forEach(s -> registry.antMatchers(s).permitAll());
        registry.anyRequest().authenticated()
                .and()
                .formLogin()
                // .loginPage("/login") //  登录地址
                .loginProcessingUrl("/user/login")// 登录成功后跳转的地址
                .permitAll()
                .successHandler(authenticationSuccessHandler)
                .and()
                .logout()
                .permitAll()
                .and()
                .authorizeRequests()
                //任何请求
                .anyRequest()
                //需要身份认证
                .authenticated()
                .and()
                //关闭跨站请求防护
                .csrf().disable()
                //前后端分离采用JWT 不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .headers().cacheControl(); // 禁用页面缓存
    }
}
