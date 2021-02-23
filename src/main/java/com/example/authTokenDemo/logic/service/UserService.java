package com.example.authTokenDemo.logic.service;

import com.example.authTokenDemo.framework.model.LoginUser;
import com.example.authTokenDemo.framework.model.Result;
import com.example.authTokenDemo.logic.dao.user.req.LoginAuth;

/**
 * @author luwl
 * @date 2021/2/23 12:30
 */
public interface UserService {

    /**
     * 执行登录验证
     *
     * @param loginAuth 登录身份验证
     */
    Result<LoginUser> doLoginValid(LoginAuth loginAuth);


    Result<LoginUser> getUserInfo();

}
