package com.spring.study.controller;

import com.spring.study.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/8/4.
 */
@Slf4j
public class BaseController {

    @Autowired
    protected UserService userService;

    public List<String> getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> list = new ArrayList<>(10);
        for (GrantedAuthority grantedAuthority : authorities) {
            log.info("权限列表：{}", grantedAuthority.getAuthority());
            list.add(grantedAuthority.getAuthority());
        }
        return list;
    }
}
