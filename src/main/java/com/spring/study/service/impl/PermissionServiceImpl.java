package com.spring.study.service.impl;

import com.spring.study.entity.Permission;
import com.spring.study.mapper.dao.PermissionDao;
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

    private final PermissionDao permissionDao;

    @Autowired
    public PermissionServiceImpl(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    @Override
    public PermissionDao getRepository() {
        return permissionDao;
    }

    @Override
    public List<Permission> findByLevelOrderBySortOrder(Integer level) {
        return permissionDao.findByLevelOrderBySortOrder(level);
    }

    @Override
    public List<Permission> findByParentIdOrderBySortOrder(String parentId) {
        return permissionDao.findByParentIdOrderBySortOrder(parentId);
    }

    @Override
    public List<Permission> findByLevelAndTypeOrderBySortOrder(Integer level, Integer type) {
        return permissionDao.findByLevelAndTypeOrderBySortOrder(level, type);
    }

    @Override
    public List<Permission> findByTypeAndParentIdOrderBySortOrder(Integer type, String parentId) {
        return permissionDao.findByTypeAndParentIdOrderBySortOrder(type, parentId);
    }
}
