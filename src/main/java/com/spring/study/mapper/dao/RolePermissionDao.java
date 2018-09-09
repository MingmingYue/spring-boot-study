package com.spring.study.mapper.dao;

import com.spring.study.entity.RolePermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/9/8.
 */
@Mapper
public interface RolePermissionDao extends BaseDao<RolePermission, String> {

    List<RolePermission> findByPermissionId(String permissionId);

    void deleteByRoleId(String roleId);
}
