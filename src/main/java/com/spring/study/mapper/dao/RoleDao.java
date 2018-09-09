package com.spring.study.mapper.dao;

import com.spring.study.entity.Role;

import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/9/9.
 */
public interface RoleDao extends BaseDao<Role, String> {

    /**
     * 获取默认角色
     * @param defaultRole
     * @return
     */
    List<Role> findByDefaultRole(Boolean defaultRole);
}
