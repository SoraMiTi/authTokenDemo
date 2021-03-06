package com.example.authTokenDemo.framework.model;


import com.example.authTokenDemo.framework.constant.CommonErrorEnum;
import com.example.authTokenDemo.util.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author luwl
 * @version [1.0.0, 2020/7/8]
 **/
public class StaticInfo {
    //IP地址
    private static final ThreadLocal<String> IP_TL = new ThreadLocal<>();
    //登录用户信息
    private static final ThreadLocal<LoginUser> LOGIN_USER_TL = new ThreadLocal<>();
    //请求路径
    private static final ThreadLocal<String> ACTION_URL_TL = new ThreadLocal<>();
    //请求接口注解：{@link ApiOperation}的Value
    private static final ThreadLocal<String[]> ACTION_API_OPERATION_TL = new ThreadLocal<>();
    //请求时间戳
    private static final ThreadLocal<Long> REQUEST_TIMESTAMP_TL = new ThreadLocal<>();
    //异常Result
    private static final ThreadLocal<Result<?>> EXCEPTION_RESULT_TL = new ThreadLocal<>();
    //请求参数
    private static final ThreadLocal<Map<String, Object>> PARAMS_TL = new ThreadLocal<>();

    private static final List<ThreadLocal<?>> THREAD_LOCAL_LIST = new ArrayList<>();

    static {
        THREAD_LOCAL_LIST.add(IP_TL);
        THREAD_LOCAL_LIST.add(LOGIN_USER_TL);
        THREAD_LOCAL_LIST.add(ACTION_URL_TL);
        THREAD_LOCAL_LIST.add(ACTION_API_OPERATION_TL);
        THREAD_LOCAL_LIST.add(REQUEST_TIMESTAMP_TL);
        THREAD_LOCAL_LIST.add(EXCEPTION_RESULT_TL);
        THREAD_LOCAL_LIST.add(PARAMS_TL);
    }

    public static String getIp() {
        return IP_TL.get();
    }

    public static void setIp(String ip) {
        IP_TL.set(ip);
    }

    public static void clearIp() {
        IP_TL.remove();
    }

    public static Long getUserId() {
        LoginUser loginUser = getLoginUser();
        if (loginUser == null) {
            throw new ServiceException(CommonErrorEnum.E_UNAUTHENTICATED);
        }
        return loginUser.getUserId();
    }

    public static LoginUser getLoginUser() {
        return LOGIN_USER_TL.get();
    }

    public static void setLoginUser(LoginUser user) {
        LOGIN_USER_TL.set(user);
    }

    public static void clearLoginUser() {
        LOGIN_USER_TL.remove();
    }

    public static String getActionUrl() {
        return ACTION_URL_TL.get();
    }

    public static void setActionUrl(String action) {
        ACTION_URL_TL.set(action);
    }

    public static void setActionApiOperation(String methodInfo, String apiRemark) {
        ACTION_API_OPERATION_TL.set(new String[]{methodInfo, apiRemark});
    }

    public static String getActionApiMethodInfo() {
        String[] values = ACTION_API_OPERATION_TL.get();
        if (values != null && values.length == 2) {
            return values[0];
        }
        return "";
    }

    public static void clearActionApiMethodInfo() {
        ACTION_API_OPERATION_TL.remove();
    }

    public static String getActionApiOperationValue() {
        String[] values = ACTION_API_OPERATION_TL.get();
        if (values != null && values.length == 2) {
            return values[1];
        }
        return "";
    }

    public static void clearAction() {
        ACTION_URL_TL.remove();
    }

    public static long getRequestTimeStamp() {
        return REQUEST_TIMESTAMP_TL.get();
    }

    public static void setRequestTimeStamp(long timeStamp) {
        REQUEST_TIMESTAMP_TL.set(timeStamp);
    }

    public static void clearRequestTimeStamp() {
        REQUEST_TIMESTAMP_TL.remove();
    }

    public static Result<?> getExceptionResult() {
        return EXCEPTION_RESULT_TL.get();
    }

    public static void setExceptionResult(Result<?> exceptionResult) {
        EXCEPTION_RESULT_TL.set(exceptionResult);
    }

    public static void clearExceptionResult() {
        EXCEPTION_RESULT_TL.remove();
    }

    public static Map<String, Object> getParamsMap() {
        return PARAMS_TL.get();
    }

    public static void setRequestParams(Map<String, Object> paramsMap) {
        PARAMS_TL.set(paramsMap);
    }

    public static void clearRequestParams(Map<String, Object> paramsMap) {
        PARAMS_TL.remove();
    }

    public static void clearThreadLocal() {
        for (ThreadLocal<?> tl : THREAD_LOCAL_LIST) {
            if (tl != null) {
                tl.remove();
            }
        }
    }

}
