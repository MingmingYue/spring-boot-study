package com.spring.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/26.
 */
@EnableSwagger2
@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
public class SpringStudyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringStudyServiceApplication.class, args);
    }
}
