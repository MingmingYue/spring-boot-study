package com.spring.study.security;

import java.lang.annotation.*;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreSecurity {
}
