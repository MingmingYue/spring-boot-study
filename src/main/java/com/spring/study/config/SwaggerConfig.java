package com.spring.study.config;

import com.spring.study.common.JwtConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /**
     * 默认token 方便调试
     */
    private static final String DEFAULT_TOKEN = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxNTg5MjE5NzM5Ny1bUk9MRV9BRE1JTiwgQVVUSF9XUklURV0iLCJleHAiOjE1MzQyNzIyOTB9.h1csdIF_OPFANIWr-0GLhB0qBp3k1Vl6ha67gaKGoeg";

    @Bean
    public Docket videoMarketApi() {
        // 添加swagger 请求头中的token参数
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        tokenPar.name(JwtConstant.TOKEN).description("令牌").defaultValue(DEFAULT_TOKEN).modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("study")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.spring.study.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("项目文档")
                .termsOfServiceUrl("http://127.0.0.1:8081")
                .contact(new Contact("study", "http://127.0.0.1:8081", "156873783@qq.com"))
                .version("1.1")
                .build();
    }
}