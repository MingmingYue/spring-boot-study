package com.spring.study.controller;

import com.spring.study.bean.Response;
import com.spring.study.entity.User;
import com.spring.study.security.IgnoreSecurity;
import com.spring.study.service.UserService;
import com.spring.study.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ZhouMingming
 * @data: Create on 2018/7/27.
 */
@Slf4j
@RestController
@Api(tags = "用户")
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @IgnoreSecurity
    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @ApiResponse(code = 200, message = "Success")
    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<Boolean> register(@RequestParam("mobile") String mobile, @RequestParam("password") String password) {
        boolean success = userService.register(mobile, password);
        return Response.success(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), success);
    }

    @ApiOperation(value = "获取用户", notes = "获取用户", httpMethod = "GET")
    @ApiResponse(code = 200, message = "User")
    @RequestMapping(value = "/register", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Response<UserVo> getUser() {
        return Response.success(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), User.builder().mobile("123456").email("123456").build().toVo());
    }
}