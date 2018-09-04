package com.spring.study.service.impl;

import com.spring.study.entity.Permission;
import com.spring.study.mapper.PermissionMapper;
import com.spring.study.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/9/4.
 */
@Service("PermissionService")
public class PermissionServiceImpl implements PermissionService {

    private final PermissionMapper permissionMapper;

    @Autowired
    public PermissionServiceImpl(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public List<Permission> getAll() {
        return permissionMapper.getAll();
    }
}
