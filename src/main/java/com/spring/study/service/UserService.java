package com.spring.study.service;


import com.spring.study.entity.User;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
public interface UserService {

    /**
     * 用户注册
     *
     * @param mobile
     * @param password
     * @return
     */
    boolean register(String mobile, String password);

    /**
     * 用户登录
     *
     * @param mobile
     * @param password
     * @return
     */
    User login(String mobile, String password);

    /**
     * 获取用户
     *
     * @return
     */
    User getUser();
}
