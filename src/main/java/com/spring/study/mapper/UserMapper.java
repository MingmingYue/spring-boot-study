package com.spring.study.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
@Mapper
public interface UserMapper {

    boolean register(@Param("mobile") String mobile, @Param("password") String password) throws Exception;
}
