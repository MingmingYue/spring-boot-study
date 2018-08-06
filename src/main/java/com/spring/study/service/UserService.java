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
     * @param username
     * @param password
     * @return
     */
    boolean register(String username, String password);

    /**
     * 获取用户
     *
     * @param username
     * @return
     */
    User getUserByName(String username);
}
