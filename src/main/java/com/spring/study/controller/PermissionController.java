package com.spring.study.controller;

import com.spring.study.bean.Response;
import com.spring.study.entity.Permission;
import com.spring.study.security.permission.MySecurityMetadataSource;
import com.spring.study.service.PermissionService;
import com.spring.study.service.RolePermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/9/11.
 */
@RestController
@Api(tags = "1.1", description = "角色管理", value = "角色管理")
@RequestMapping(value = "/permission")
public class PermissionController {

    private static final Logger log = LogManager.getLogger(UserController.class);

    private final PermissionService permissionService;
    private final RolePermissionService rolePermissionService;
    private final MySecurityMetadataSource mySecurityMetadataSource;

    @Autowired
    public PermissionController(PermissionService permissionService,
                                RolePermissionService rolePermissionService,
                                MySecurityMetadataSource mySecurityMetadataSource) {
        this.permissionService = permissionService;
        this.rolePermissionService = rolePermissionService;
        this.mySecurityMetadataSource = mySecurityMetadataSource;
    }

    @PostMapping(value = "add", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "添加权限")
    public Response<Permission> add(@ModelAttribute Permission permission) {
        Permission p = permissionService.save(permission);
        mySecurityMetadataSource.loadResourceDefine();
        return Response.success(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), p);
    }
}
