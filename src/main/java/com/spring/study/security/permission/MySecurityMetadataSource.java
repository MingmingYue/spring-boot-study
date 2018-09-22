package com.spring.study.security.permission;

import cn.hutool.core.util.StrUtil;
import com.spring.study.entity.Permission;
import com.spring.study.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.*;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/9/4.
 */
@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final PermissionService permissionService;

    private Map<String, Collection<ConfigAttribute>> map = null;

    @Autowired
    public MySecurityMetadataSource(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    /**
     * 加载权限列表中的所有权限
     */
    public void loadResourceDefine() {
        map = new HashMap<>(32);
        Collection<ConfigAttribute> configAttributes;
        ConfigAttribute cfg;
        List<Permission> permissions = permissionService.getAll();
        for (Permission permission : permissions) {
            configAttributes = new ArrayList<>(8);
            cfg = new SecurityConfig(permission.getTitle());
            // 作为MyAccessDecisionManager中的decide的第三个参数
            configAttributes.add(cfg);
            // 用权限的path作为map的key 用ConfigAttribute的集合作为value
            map.put(permission.getPath(), configAttributes);
        }
    }

    /**
     * 判定用户请求的url是否在权限列表中
     * 如果在权限列表中 则返回给decide方法 用来判定用户是否有此权限
     * 如果不在权限表中则放行
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if (map == null) {
            loadResourceDefine();
        }
        //Object中包含用户请求request
        String url = ((FilterInvocation) object).getRequestUrl();
        PathMatcher pathMatcher = new AntPathMatcher();
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            String resURL = iterator.next();
            if (StrUtil.isNotBlank(resURL) && pathMatcher.match(resURL, url)) {
                return map.get(resURL);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
