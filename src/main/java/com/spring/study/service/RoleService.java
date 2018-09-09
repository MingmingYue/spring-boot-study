package com.spring.study.service;

import com.spring.study.entity.Role;

import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/9/8.
 */
public interface RoleService extends BaseService<Role, String> {

    /**
     * 获取默认角色
     *
     * @param defaultRole
     * @return
     */
    List<Role> findByDefaultRole(Boolean defaultRole);
}
