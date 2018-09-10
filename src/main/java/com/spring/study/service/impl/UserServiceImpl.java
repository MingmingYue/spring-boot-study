package com.spring.study.service.impl;

import com.spring.study.entity.User;
import com.spring.study.mapper.PermissionMapper;
import com.spring.study.mapper.dao.UserDao;
import com.spring.study.mapper.UserRoleMapper;
import com.spring.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
@Transactional  // 要加这个注解否则会报  could not initialize proxy
@Service("UserService")
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserRoleMapper userRoleMapper;
    private final PermissionMapper permissionMapper;

    @Autowired
    public UserServiceImpl(UserDao userDao,
                           UserRoleMapper userRoleMapper,
                           PermissionMapper permissionMapper) {
        this.userDao = userDao;
        this.userRoleMapper = userRoleMapper;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public UserDao getRepository() {
        return userDao;
    }

    @Override
    public User getUserByName(String username) {
        User user = userDao.getUserByUsername(username);
        user.setRoles(userRoleMapper.findByUserId(user.getId()));
        user.setPermissions(permissionMapper.findByUserId(user.getId()));
        return user;
    }
}
