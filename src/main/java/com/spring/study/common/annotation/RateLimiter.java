package com.spring.study.common.annotation;

import java.lang.annotation.*;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/10/21.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {

    int limit() default 5;
    int timeout() default 1000;
}
