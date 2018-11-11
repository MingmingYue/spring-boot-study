package com.spring.study.common.annotation;

import java.lang.annotation.*;

/**
 * 系统日志自定义注解
 *
 * @author: ZhouMingming
 * @data: Create on 2018/10/9.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {

    String description() default "";
}
