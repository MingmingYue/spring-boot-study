package com.spring.study.security.impl;

import com.spring.study.entity.User;
import com.spring.study.security.SecurityUserDetails;
import com.spring.study.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/8/4.
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByName(username);
        if (null == user) {
            throw new UsernameNotFoundException(username);
        }
        return new SecurityUserDetails(user);
    }

}
