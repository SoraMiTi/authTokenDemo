package com.example.demo.logic.controller;

import com.example.demo.framework.model.Result;
import com.example.demo.logic.dao.user.req.LoginAuth;
import com.example.demo.logic.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luwl
 * @date 2021/2/23 12:29
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(value = "系统用户表", tags = "系统用户表接口")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "登录")
    public Result<?> login(@RequestBody LoginAuth loginAuth) {
        return userService.doLoginValid(loginAuth);
    }

    @RequestMapping(value = "/getUserInfo", method = RequestMethod.POST)
    @ApiOperation(value = "查看当前登录用户信息")
    public Result<?> getUserInfo() {
        return userService.getUserInfo();
    }
}
