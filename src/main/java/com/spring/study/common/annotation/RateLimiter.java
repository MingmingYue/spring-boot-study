package com.spring.study.common.annotation;

import com.spring.study.enums.LimitType;

import java.lang.annotation.*;

/**
 * 限流
 * @author: ZhouMingming
 * @data: Create on 2018/10/21.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface RateLimiter {

    /**
     * 资源的名字
     *
     * @return String
     */
    String name() default "";

    /**
     * 资源的key
     *
     * @return String
     */
    String key() default "";

    /**
     * Key的prefix
     *
     * @return String
     */
    String prefix() default "";

    /**
     * 给定的时间段
     * 单位秒
     *
     * @return int
     */
    int period();

    /**
     * 最多的访问限制次数
     *
     * @return int
     */
    int count();

    /**
     * 类型
     *
     * @return LimitType
     */
    LimitType limitType() default LimitType.CUSTOMER;
}
