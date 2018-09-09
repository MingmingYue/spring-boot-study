package com.spring.study.service.impl;

import com.spring.study.entity.RolePermission;
import com.spring.study.mapper.RolePermissionMapper;
import com.spring.study.service.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/9/8.
 */
@Service("RolePermissionService")
public class RolePermissionServiceImpl implements RolePermissionService {

    private final RolePermissionMapper rolePermissionMapper;

    @Autowired
    public RolePermissionServiceImpl(RolePermissionMapper rolePermissionMapper) {
        this.rolePermissionMapper = rolePermissionMapper;
    }

    @Override
    public RolePermissionMapper getRepository() {
        return rolePermissionMapper;
    }

    @Override
    public void deleteByRoleId(String roleId) {

    }

    @Override
    public List<RolePermission> findByPermissionId(String permissionId) {
        return null;
    }


}
