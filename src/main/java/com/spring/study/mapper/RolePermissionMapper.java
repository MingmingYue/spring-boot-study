package com.spring.study.mapper;

import com.spring.study.entity.RolePermission;
import com.spring.study.mapper.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/9/8.
 */
@Mapper
public interface RolePermissionMapper extends BaseDao<RolePermission, String> {

    List<RolePermission> findByPermissionId(String permissionId);

    void deleteByRoleId(String roleId);
}
