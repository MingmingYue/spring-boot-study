package com.spring.study.service;

import com.spring.study.entity.RolePermission;

import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/9/8.
 */
public interface RolePermissionService extends BaseService<RolePermission,String>{

    void deleteByRoleId(String roleId);

    List<RolePermission> findByPermissionId(String permissionId);
}
