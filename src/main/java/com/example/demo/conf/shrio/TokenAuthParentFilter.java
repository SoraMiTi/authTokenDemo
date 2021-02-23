package com.example.demo.conf.shrio;

import com.example.demo.framework.constant.CommonErrorEnum;
import com.example.demo.framework.constant.Constant;
import com.example.demo.framework.model.Result;
import com.example.demo.util.FastJsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author luwl
 * @version [1.0.0, 2021/1/7]
 **/
@Slf4j
public class TokenAuthParentFilter extends BasicHttpAuthenticationFilter {

    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader(Constant.HEADER_TOKEN_NAME);
        return StringUtils.isNotEmpty(token);
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) {
        this.sendChallenge(request, response);
        return false;
    }

    @Override
    protected boolean sendChallenge(ServletRequest request, ServletResponse response) {
        //Filter中无法进行统一异常处理
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        HttpServletResponse httpResponse = WebUtils.toHttp(response);
        httpResponse.setStatus(HttpStatus.OK.value());
        httpResponse.setCharacterEncoding("utf-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        Result<Object> responseResult = Result.error();
        String authInfo = (String) httpRequest.getAttribute("authInfo");
        if ("loginExpire".equals(authInfo)) {
            responseResult = Result.error(CommonErrorEnum.E_LOGIN_TIMEOUT);
        }
        if ("unLogin".equals(authInfo)) {
            responseResult = Result.error(CommonErrorEnum.E_UNAUTHENTICATED);
        }
        try (PrintWriter out = httpResponse.getWriter()) {
            String responseJson = FastJsonUtil.beanToJsonStr(responseResult);
            out.print(responseJson);
        } catch (IOException e) {
            log.error("sendChallenge error：", e);
        }
        return false;
    }
}
