package com.spring.study.service.impl;

import com.spring.study.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Override
    public boolean register(String mobile, String password) {
        return true;
    }
}
