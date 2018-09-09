package com.spring.study.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.spring.study.entity.Permission;
import com.spring.study.mapper.PermissionMapper;
import com.spring.study.service.IPermissionService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/9/9.
 */
@Service("IPermissionService")
public class IPermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    private final PermissionMapper permissionMapper;

    public IPermissionServiceImpl(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public List<Permission> findByUserId(String userId) {
        return permissionMapper.findByUserId(userId);
    }

    @Override
    public List<Permission> findByRoleId(String roleId) {
        return permissionMapper.findByRoleId(roleId);
    }
}
