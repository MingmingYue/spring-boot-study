package com.spring.study.service.impl;

import com.spring.study.entity.RolePermission;
import com.spring.study.mapper.dao.RolePermissionDao;
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

    private final RolePermissionDao rolePermissionDao;

    @Autowired
    public RolePermissionServiceImpl(RolePermissionDao rolePermissionDao) {
        this.rolePermissionDao = rolePermissionDao;
    }

    @Override
    public RolePermissionDao getRepository() {
        return rolePermissionDao;
    }

    @Override
    public void deleteByRoleId(String roleId) {
        rolePermissionDao.deleteByRoleId(roleId);
    }

    @Override
    public List<RolePermission> findByPermissionId(String permissionId) {
        return rolePermissionDao.findByPermissionId(permissionId);
    }
}
