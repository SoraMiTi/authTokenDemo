package com.example.authTokenDemo.conf.interceptor;

import com.example.authTokenDemo.framework.model.StaticInfo;
import com.example.authTokenDemo.util.IPUtil;
import com.example.authTokenDemo.util.redis.RedisUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author luwl
 * @version [1.0.0, 2020/7/28]
 **/
@Slf4j
public class SpringMvcInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisUtils redisUtils;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String remoteIp = IPUtil.getClientIp(request);
        String localIp = IPUtil.getLocalIP();
        log.info("SpringMvcInterceptor ——>  remoteIP:{}, localIP: {}, 访问路径:{}", remoteIp, localIp, request.getRequestURI());
        String methodInfo = "";
        String apiRemark = "";
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = ((HandlerMethod) handler);
            methodInfo = handlerMethod.toString();
            Method method = handlerMethod.getMethod();
            ApiOperation annotation = method.getAnnotation(ApiOperation.class);
            if (annotation != null) {
                apiRemark = annotation.value();
            }
        }
        return super.preHandle(request, response, handler);
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        //TODO 如果有需要,可以在这里对调用接口做记录和分析
        log.info("SpringMvcInterceptor ——>  访问结束。");
        StaticInfo.clearThreadLocal();
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

}
