package com.spring.study.mapper.dao;

import com.spring.study.entity.Permission;

import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/9/9.
 */
public interface PermissionDao extends BaseDao<Permission, String> {

    /**
     * 通过层级查找
     * 默认升序
     * @param level
     * @return
     */
    List<Permission> findByLevelOrderBySortOrder(Integer level);

    /**
     * 通过parendId查找
     * @param parentId
     * @return
     */
    List<Permission> findByParentIdOrderBySortOrder(String parentId);

    /**
     * 通过类型和层级查找
     * @param level
     * @param type
     * @return
     */
    List<Permission> findByLevelAndTypeOrderBySortOrder(Integer level, Integer type);

    /**
     * 通过parendId和类型查找
     * @param type
     * @param parentId
     * @return
     */
    List<Permission> findByTypeAndParentIdOrderBySortOrder(Integer type, String parentId);
}
