package com.spring.study.mapper;

import com.spring.study.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
@Mapper
public interface UserMapper {

    /**
     * 查找用户
     *
     * @param username
     * @return
     */
    User getUserByName(@Param("username") String username);
}
