package com.spring.study.service;

import com.spring.study.entity.UserRole;

import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/9/9.
 */
public interface UserRoleService extends BaseService<UserRole, String> {

    List<UserRole> findByRoleId(String roleId);

    void deleteByUserId(String userId);
}
