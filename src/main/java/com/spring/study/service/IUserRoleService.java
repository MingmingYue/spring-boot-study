package com.spring.study.service;

import com.baomidou.mybatisplus.service.IService;
import com.spring.study.entity.Role;
import com.spring.study.entity.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/9/9.
 */
public interface IUserRoleService extends IService<UserRole> {

    List<Role> findByUserId(@Param("userId") String userId);
}
