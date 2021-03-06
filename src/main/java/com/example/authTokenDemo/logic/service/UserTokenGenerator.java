package com.example.authTokenDemo.logic.service;


import com.example.authTokenDemo.framework.model.LoginUser;

/**
 * 用户token
 *
 * @author luwl
 * @version [1.0.0, 2021/1/23]
 **/
public interface UserTokenGenerator {
    /**
     * 生成用户Token
     *
     * @param loginUser 登录用户
     * @return {@link String}
     */
    String generatorUserToken(LoginUser loginUser);
}
