package com.example.demo.framework.model;

import java.io.Serializable;

/**
 * 登录用户信息
 * @author youfang
 * @version [1.0.0, 2020/6/30]
 **/
public class LoginUser implements Serializable {
    private static final long serialVersionUID = 1530988057621544478L;

    /**
     * 登录token
     */
    private String token;
    /**
     * 用户id
     */
    private Long	userId;
    /**
     * 用户真实姓名
     */
    private String	userName;
    /**
     * 性别 1男 2女
     */
    private Integer	sex;
    /**
     * 用户手机号
     */
    private String	phone;
    /**
     * 状态 0禁用 1正常
     */
    private Integer	status;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}
