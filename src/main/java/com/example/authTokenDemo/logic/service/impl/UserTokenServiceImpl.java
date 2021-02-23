package com.example.authTokenDemo.logic.service.impl;

import com.example.authTokenDemo.framework.constant.RedisKeyConstant;
import com.example.authTokenDemo.framework.model.LoginUser;
import com.example.authTokenDemo.logic.service.UserTokenService;
import com.example.authTokenDemo.util.FastJsonUtil;
import com.example.authTokenDemo.util.redis.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luwl
 * @date 2021/2/23 13:34
 */
@Service
public class UserTokenServiceImpl implements UserTokenService {

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 获取登录用户
     *
     * @param token 令牌
     * @return {@link LoginUser}
     */
    @Override
    public LoginUser getLoginUser(String token) {
        if (StringUtils.isNotEmpty(token)) {
            String userId = redisUtils.get(RedisKeyConstant.USER_LOGIN_TOKEN + token);
            String loginStr = redisUtils.get(RedisKeyConstant.USER_LOGIN_INFO + userId);
            LoginUser loginUser = FastJsonUtil.parseObject(loginStr, LoginUser.class);
            if (loginUser != null) {
                loginUser.setToken(token);
                return loginUser;
            }
        }
        return null;
    }
}
