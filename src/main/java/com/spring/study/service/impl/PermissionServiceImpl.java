package com.spring.study.service.impl;

import com.spring.study.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/9/4.
 */
@Service("PermissionService")
public class PermissionServiceImpl {

    private final PermissionMapper permissionMapper;

    @Autowired
    public PermissionServiceImpl(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

}
