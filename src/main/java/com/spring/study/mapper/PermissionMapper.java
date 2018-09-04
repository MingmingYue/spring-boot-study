package com.spring.study.mapper;

import com.spring.study.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/9/2.
 */
@Mapper
public interface PermissionMapper {

    List<Permission> getAll();

    List<Permission> findByUserId(@Param("userId") String userId);

    List<Permission> findByRoleId(@Param("roleId") String roleId);
}
