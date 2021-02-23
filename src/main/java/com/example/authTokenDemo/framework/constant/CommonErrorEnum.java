package com.example.authTokenDemo.framework.constant;

import com.example.authTokenDemo.util.exception.ErrorEnum;

/**
 * 统一错误码/错误信息
 *
 * @author luwl
 * @version [1.0.0, 2020/7/9]
 **/
public enum CommonErrorEnum implements ErrorEnum<CommonErrorEnum> {
    /**
     * 公共
     */
    E_PARAM_ERROR(3000, "请求参数错误，请检查！"),
    E_NOT_FOUNT(3010, "请求接口不存在，请检查！"),
    /**
     * 登录
     */
    E_UNAUTHENTICATED(4001, "未登录，请先登录！"),
    E_LOGIN_TIMEOUT(4001, "登录超时，请重新登录！"),
    E_UN_KNOW(99999, "未知异常！"),
    E_UNAUTHORIZED(4003, "对不起，您没有访问权限，如需要请联系管理员！"),
    ;

    // 成员变量
    private int code;

    private String message;

    CommonErrorEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // 普通方法
    public static String getMessage(int code) {
        for (CommonErrorEnum c : CommonErrorEnum.values()) {
            if (c.getCode() == code) {
                return c.message;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 返回枚举项的 code
     */
    @Override
    public int code() {
        return this.code;
    }

    /**
     * 返回枚举项的 message
     */
    @Override
    public String message() {
        return this.message;
    }

    /**
     * 返回枚举对象
     */
    @Override
    public CommonErrorEnum get() {
        return this;
    }
}
