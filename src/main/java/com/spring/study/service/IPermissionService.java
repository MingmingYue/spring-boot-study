package com.spring.study.service;

import com.baomidou.mybatisplus.service.IService;
import com.spring.study.entity.Permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/9/9.
 */
public interface IPermissionService extends IService<Permission> {

    List<Permission> findByUserId(String userId);

    List<Permission> findByRoleId(@Param("roleId") String roleId);
}
