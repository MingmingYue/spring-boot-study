package com.spring.study.mapper.dao;

import com.spring.study.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
@Mapper
public interface UserDao extends BaseDao<User, String> {


    User getUserByUsername(String username);

    /**
     * 通过用户名和状态获取用户
     * @param username
     * @param status
     * @return
     */
    List<User> findByUsernameAndStatus(String username, Integer status);

    /**
     * 通过状态和类型获取用户
     * @param status
     * @param type
     * @return
     */
    List<User> findByStatusAndType(Integer status, Integer type);
}
