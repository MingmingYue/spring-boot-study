package com.spring.study.controller;

import com.spring.study.bean.Response;
import com.spring.study.entity.User;
import com.spring.study.security.IgnoreSecurity;
import com.spring.study.security.TokenManager;
import com.spring.study.service.UserService;
import com.spring.study.vo.UserVo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
@Slf4j
@RestController
@Api(tags = "1.1", description = "用户管理", value = "用户管理")
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;
    private final TokenManager tokenManager;

    @Autowired
    public UserController(UserService userService, TokenManager tokenManager) {
        this.userService = userService;
        this.tokenManager = tokenManager;
    }

    @IgnoreSecurity
    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "注册账号"),
            @ApiImplicitParam(name = "password", value = "密码")})
    @ApiResponse(code = 200, message = "OK")
    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<Boolean> register(@RequestParam("mobile") String mobile, @RequestParam("password") String password) {
        boolean success = userService.register(mobile, password);
        return Response.success(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), success);
    }

    @IgnoreSecurity
    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mobile", value = "账号"),
            @ApiImplicitParam(name = "password", value = "密码")})
    @ApiResponse(code = 200, message = "OK")
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<UserVo> login(@RequestParam("mobile") String mobile, @RequestParam("password") String password) {
        UserVo user = userService.login(mobile, password).toVo();
        user.setToken(tokenManager.createToken(mobile, user.getId()));
        return Response.success(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), user);
    }

    @ApiOperation(value = "获取用户", notes = "获取用户", httpMethod = "GET")
    @ApiResponse(code = 200, message = "OK")
    @GetMapping(value = "/getUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<UserVo> getUser() {
        User user = userService.getUser();
        return Response.success(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), user.toVo());
    }
}
