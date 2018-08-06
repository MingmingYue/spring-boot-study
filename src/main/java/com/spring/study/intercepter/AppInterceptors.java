package com.spring.study.intercepter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/8/1.
 */
@Configuration
public class AppInterceptors extends WebMvcConfigurerAdapter {

    /**
     * 添加拦截器
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
//        super.addInterceptors(registry);
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
                .allowedOrigins("127.0.0.1")
                .allowedMethods("POST", "GET", "OPTIONS")
                .allowedHeaders("Content-Type", "Authorization", "Accept", "Token");
    }
}


