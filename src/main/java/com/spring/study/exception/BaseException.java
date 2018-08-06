package com.spring.study.exception;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/8/3.
 */
public class BaseException extends RuntimeException {

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
