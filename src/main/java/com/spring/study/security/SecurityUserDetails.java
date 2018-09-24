package com.spring.study.security;

import cn.hutool.core.util.StrUtil;
import com.spring.study.common.CommonConstant;
import com.spring.study.entity.Permission;
import com.spring.study.entity.Role;
import com.spring.study.entity.User;
import cn.hutool.core.collection.CollUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/8/28.
 */
public class SecurityUserDetails extends User implements UserDetails {

    private static final Logger log = LogManager.getLogger(SecurityUserDetails.class);


    public SecurityUserDetails(User user) {
        if (user != null) {
            this.setUsername(user.getUsername());
            this.setPassword(user.getPassword());
            this.setStatus(user.getStatus());
            this.setRoles(user.getRoles());
            this.setPermissions(user.getPermissions());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        List<Permission> permissions = this.getPermissions();
        if (CollUtil.isNotEmpty(permissions) && permissions.get(0) != null) {
            permissions.stream().forEach(permission -> {
                if (StrUtil.isNotBlank(permission.getTitle())
                        && StrUtil.isNotBlank(permission.getPath())) {
                    authorityList.add(new SimpleGrantedAuthority(permission.getTitle()));
                }
            });
        }
        List<Role> roles = this.getRoles();
        if (roles != null && roles.size() > 0) {
            roles.forEach(item -> {
                if (StrUtil.isNotBlank(item.getName())) {
                    authorityList.add(new SimpleGrantedAuthority(item.getName()));
                }
            });
        }
        return authorityList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return CommonConstant.USER_STATUS_LOCK.equals(this.getStatus()) ? false : true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return CommonConstant.USER_STATUS_NORMAL.equals(this.getStatus()) ? true : false;
    }
}
