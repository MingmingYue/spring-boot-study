package com.spring.study.service.impl;

import com.spring.study.common.TimeUtils;
import com.spring.study.entity.User;
import com.spring.study.mapper.UserMapper;
import com.spring.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public boolean register(String mobile, String password) {
        long curr = TimeUtils.getCurr();
        User user = User.builder()
//                .mobile(mobile)
                .password(password)
//                .createAt(curr)
//                .updateAt(curr)
                .build();
        return userMapper.register(user);
    }

    @Override
    public User login(String mobile, String password) {
        return userMapper.login(mobile, password);
    }

    @Override
    public User getUser() {
        return Optional.ofNullable(userMapper.getUserById(7)).orElseThrow(() -> new IllegalAccessError("用户不存在"));
    }

    @Override
    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }
}
