package com.spring.study.controller;

import com.spring.study.bean.Response;
import com.spring.study.entity.User;
import com.spring.study.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "获取用户", notes = "获取用户", httpMethod = "GET")
    @ApiResponse(code = 200, message = "OK")
    @GetMapping(value = "/getUser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<User> getUser() {
        Optional<Authentication> authentication = Optional.of(SecurityContextHolder.getContext().getAuthentication());
        User user = userService.getUserByName(((UserDetails) authentication.get().getPrincipal()).getUsername());
        return Response.success(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), user);
    }

    @RequestMapping(value = "/needLogin", method = RequestMethod.GET)
    @ApiOperation(value = "没有登录")
    public Response<String> needLogin() {
        return Response.failure(401, "您还未登录");
    }
}
