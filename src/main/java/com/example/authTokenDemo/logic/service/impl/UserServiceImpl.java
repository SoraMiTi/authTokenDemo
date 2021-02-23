package com.example.authTokenDemo.logic.service.impl;

import com.alibaba.fastjson.JSON;
import com.example.authTokenDemo.framework.constant.RedisKeyConstant;
import com.example.authTokenDemo.framework.model.LoginUser;
import com.example.authTokenDemo.framework.model.Result;
import com.example.authTokenDemo.framework.model.StaticInfo;
import com.example.authTokenDemo.logic.dao.user.req.LoginAuth;
import com.example.authTokenDemo.logic.service.UserService;
import com.example.authTokenDemo.logic.service.UserTokenGenerator;
import com.example.authTokenDemo.util.BeanCopyUtil;
import com.example.authTokenDemo.util.exception.ServiceException;
import com.example.authTokenDemo.util.redis.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author luwl
 * @date 2021/2/23 12:30
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private UserTokenGenerator userTokenGenerator;

    /**
     * 执行登录验证
     *
     * @param loginAuth 登录身份验证
     */
    @Override
    public Result<LoginUser> doLoginValid(LoginAuth loginAuth) {
        LoginUser loginUser;
        //TODO 这里使用明文密码 应该根据用户名或者手机号查询数据,比较md5加密后的密码
        if ("admin".equals(loginAuth.getUserName()) || "1".equals(loginAuth.getPhone())) {
            if (!"1".equals(loginAuth.getPassword())) {
                throw new ServiceException("用户密码不正确");
            } else {
                loginUser = BeanCopyUtil.copy(loginAuth, LoginUser.class);
            }
        } else {
            throw new ServiceException("用户账号不正确");
        }
        loginUser.setUserId(1L);
        saveTokenToRedis(loginUser, 24 * 60 * 60);
        return Result.success(loginUser);
    }

    @Override
    public Result<LoginUser> getUserInfo() {
        return Result.success(StaticInfo.getLoginUser());
    }

    /**
     * 保存token到缓存中
     *
     * @param loginUser
     * @param timeOut   超时时间 -1 不超时
     */
    private void saveTokenToRedis(LoginUser loginUser, long timeOut) {
        //获取token
        String token = userTokenGenerator.generatorUserToken(loginUser);
        String userLoginIdKey = RedisKeyConstant.USER_LOGIN_ID + loginUser.getUserId().toString();
        //登录的时候、存在TOKEN
        String existToken = redisUtils.get(userLoginIdKey);
        if (StringUtils.isNotEmpty(existToken)) {
            //删除之前登陆的TOKEN
            redisUtils.delete(RedisKeyConstant.USER_LOGIN_TOKEN + existToken);
        }
        //保存为 token-userid的形式
        redisUtils.set(RedisKeyConstant.USER_LOGIN_TOKEN + token, loginUser.getUserId().toString(), timeOut);
        //保存为 userid-token的形式
        redisUtils.set(userLoginIdKey, token, timeOut);
        //保存用户基础信息
        redisUtils.set(RedisKeyConstant.USER_LOGIN_INFO + loginUser.getUserId().toString(), JSON.toJSONString(loginUser), timeOut);
        loginUser.setToken(token);
    }

}
