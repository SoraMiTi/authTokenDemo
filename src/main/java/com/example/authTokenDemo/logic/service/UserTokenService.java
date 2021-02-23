package com.example.authTokenDemo.logic.service;

import com.example.authTokenDemo.framework.model.LoginUser;

/**
 * @author luwl
 * @date 2021/2/23 13:33
 */
public interface UserTokenService {

    /**
     * 获取登录用户
     *
     * @param token 令牌
     * @return {@link LoginUser}
     */
    LoginUser getLoginUser(String token);

}
