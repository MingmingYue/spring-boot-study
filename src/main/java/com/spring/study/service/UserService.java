package com.spring.study.service;


import com.spring.study.entity.User;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
public interface UserService  {

    User getUserByName(String username);
}
