package com.spring.study.controller;

import com.spring.study.bean.Response;
import com.spring.study.common.CommonConstant;
import com.spring.study.common.annotation.RateLimiter;
import com.spring.study.common.annotation.SystemLog;
import com.spring.study.entity.Role;
import com.spring.study.entity.User;
import com.spring.study.entity.UserRole;
import com.spring.study.enums.ErrorType;
import com.spring.study.service.IUserRoleService;
import com.spring.study.service.RoleService;
import com.spring.study.service.UserService;
import io.swagger.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
@RestController
@Api(tags = "1.1", description = "用户管理", value = "用户管理")
@RequestMapping(value = "/user")
public class UserController {

    private static final Logger log = LogManager.getLogger(UserController.class);

    private final UserService userService;
    private final RoleService roleService;
    private final IUserRoleService iUserRoleService;

    @Autowired
    public UserController(UserService userService,
                          RoleService roleService,
                          IUserRoleService iUserRoleService) {
        this.userService = userService;
        this.roleService = roleService;
        this.iUserRoleService = iUserRoleService;
    }

    @GetMapping(value = "/getUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取用户", notes = "通过token获取用户", httpMethod = "GET")
    @RateLimiter(key = "getUser", period = 100, count = 2)
    @ApiResponse(code = 200, message = "OK")
    @SystemLog(description = "获取用户")
    public Response<User> getUser() {
        Optional<Authentication> authentication = Optional.of(SecurityContextHolder.getContext().getAuthentication());
        User user = userService.getUserByName(((UserDetails) authentication.get().getPrincipal()).getUsername());
        return Response.success(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), user);
    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @ApiResponse(code = 200, message = "OK")
    @GetMapping(value = "/registerUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<User> registerUser(@ModelAttribute User u) {
        String encryptPass = new BCryptPasswordEncoder().encode(u.getPassword());
        u.setPassword(encryptPass);
        u.setType(CommonConstant.USER_TYPE_NORMAL);
        User user = userService.save(u);
        if (user == null) {
            return Response.failure(ErrorType.REGISTER_ERROR.value(), ErrorType.REGISTER_ERROR.reasonPhrase());
        }
        // 默认角色
        List<Role> roleList = roleService.findByDefaultRole(true);
        if (roleList != null && roleList.size() > 0) {
            for (Role role : roleList) {
                UserRole ur = new UserRole();
                ur.setUserId(user.getId());
                ur.setRoleId(role.getId());
                iUserRoleService.insert(ur);
            }
        }
        return Response.success(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), user);
    }

    @GetMapping(value = "/needLogin")
    @ApiOperation(value = "没有登录")
    public Response<String> needLogin() {
        return Response.failure(ErrorType.NOT_LOGIN_IN.value(), ErrorType.NOT_LOGIN_IN.reasonPhrase());
    }
}
