package com.spring.study.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.spring.study.entity.Role;
import com.spring.study.entity.UserRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/9/3.
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<Role> findByUserId(@Param("userId") String userId);
}
