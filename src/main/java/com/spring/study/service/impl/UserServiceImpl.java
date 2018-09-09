package com.spring.study.service.impl;

import com.spring.study.entity.User;
import com.spring.study.mapper.PermissionMapper;
import com.spring.study.mapper.UserMapper;
import com.spring.study.mapper.UserRoleMapper;
import com.spring.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
@Service("UserService")
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRoleMapper userRoleMapper;
    private final PermissionMapper permissionMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper,
                           UserRoleMapper userRoleMapper,
                           PermissionMapper permissionMapper) {
        this.userMapper = userMapper;
        this.userRoleMapper = userRoleMapper;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public UserMapper  getRepository() {
        return userMapper;
    }

    @Override
    public User getUserByName(String username) {
        User user = userMapper.getUserByName(username);
        user.setRoles(userRoleMapper.findByUserId(user.getId()));
        user.setPermissions(permissionMapper.findByUserId(user.getId()));
        return user;
    }


}
