package com.spring.study.mapper;

import com.spring.study.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/9/3.
 */
@Mapper
public interface UserRoleMapper {

    List<Role> findByUserId(@Param("userId") String userId);
}
