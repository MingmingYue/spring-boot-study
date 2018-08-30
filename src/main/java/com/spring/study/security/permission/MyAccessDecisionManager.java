package com.spring.study.security.permission;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * 权限管理决断器
 *
 * @author: ZhouMingming
 * @data: Create on 2018/8/30.
 */
@Component
public class MyAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (null == configAttributes) {
            return;
        }
        Iterator<ConfigAttribute> iterable = configAttributes.iterator();
        while (iterable.hasNext()) {
            ConfigAttribute c = iterable.next();
            String needRole = c.getAttribute();
            authentication.getAuthorities().stream().forEach(o -> {
                if (needRole.trim().equals(o.getAuthority())) {
                    return;
                }
            });
        }
        throw new AccessDeniedException("没有访问权限");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
