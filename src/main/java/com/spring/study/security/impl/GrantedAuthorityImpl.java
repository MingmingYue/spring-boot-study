package com.spring.study.security.impl;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/8/3.
 */
@Data
@AllArgsConstructor
public class GrantedAuthorityImpl implements GrantedAuthority {

    private String authority;
}
