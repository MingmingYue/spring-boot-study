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

    boolean register(User user);
}
