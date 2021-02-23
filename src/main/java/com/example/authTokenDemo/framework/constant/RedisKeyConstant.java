package com.example.authTokenDemo.framework.constant;

/**
 * @author luwl
 * @version [1.0.0, 2020/8/17]
 **/
public class RedisKeyConstant {
    /**
     * 登录用户（根据用户TOKEN查询用户ID） TOKEN:USERID
     */
    public static final String USER_LOGIN_TOKEN = "USER:LOGIN:TOKEN:";
    /**
     * 登录用户（根据用户ID查询TOKEN） USERID:TOKEN
     */
    public static final String USER_LOGIN_ID = "USER:LOGIN:ID:";
    /**
     * 登录用户（根据用户ID查询登录信息） USERID:LOGINUSER
     */
    public static final String USER_LOGIN_INFO = "USER:LOGIN:USER:";
}
