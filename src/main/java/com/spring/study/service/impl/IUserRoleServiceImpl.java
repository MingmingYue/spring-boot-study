package com.spring.study.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spring.study.entity.Role;
import com.spring.study.entity.UserRole;
import com.spring.study.mapper.UserRoleMapper;
import com.spring.study.service.IUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/9/9.
 */
@Service("IUserRoleService")
public class IUserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    private final UserRoleMapper userRoleMapper;

    @Autowired
    public IUserRoleServiceImpl(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public List<Role> findByUserId(String userId) {
        return userRoleMapper.findByUserId(userId);
    }
}
