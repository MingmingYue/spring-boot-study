package com.spring.study.controller;

import com.spring.study.bean.Response;
import com.spring.study.entity.RolePermission;
import com.spring.study.service.RolePermissionService;
import com.spring.study.service.RoleService;
import com.spring.study.service.UserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/9/11.
 */
@RestController
@Api(tags = "1.1", description = "角色管理", value = "角色管理")
@RequestMapping(value = "/role")
public class RoleController {

    private static final Logger log = LogManager.getLogger(UserController.class);

    private final RoleService roleService;
    private final UserRoleService userRoleService;
    private final RolePermissionService rolePermissionService;

    @Autowired
    public RoleController(RoleService roleService,
                          UserRoleService userRoleService,
                          RolePermissionService rolePermissionService) {
        this.roleService = roleService;
        this.userRoleService = userRoleService;
        this.rolePermissionService = rolePermissionService;
    }

    @PostMapping(value = "editRolePerm/{roleId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "编辑角色权限")
    public Response<Boolean> editRolePerm(@PathVariable String roleId,
                                          @RequestParam(required = false) String[] permIds) {
        rolePermissionService.deleteByRoleId(roleId);
        for (String permId : permIds) {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permId);
            rolePermissionService.save(rolePermission);
        }
        return Response.success(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), true);
    }
}
