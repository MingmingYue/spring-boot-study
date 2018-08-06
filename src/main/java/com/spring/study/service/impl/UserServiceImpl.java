package com.spring.study.service.impl;

import com.spring.study.common.TimeUtils;
import com.spring.study.entity.User;
import com.spring.study.mapper.UserMapper;
import com.spring.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean register(String username, String password) {
        long curr = TimeUtils.getCurr();
        User user = User.builder()
                .username(username)
                .password(DigestUtils.md5DigestAsHex(password.getBytes()))
                .createAt(curr)
                .updateAt(curr)
                .build();
        return userMapper.register(user);
    }

    @Override
    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }
}
