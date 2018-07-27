package com.spring.study.service;


/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
public interface UserService {

    /**
     * 用户注册
     * @param mobile
     * @param password
     * @return
     */
    boolean register(String mobile, String password);
}
