package com.spring.study.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private int id;
    private String mobile;
    private String password;
    private String username;
    private String email;
    private String desc;
    private long createAt;
    private long updateAt;
}
