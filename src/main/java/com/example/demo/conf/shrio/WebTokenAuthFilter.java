package com.example.demo.conf.shrio;

import com.example.demo.framework.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


/**
 * WebToken授权Filter
 *
 * @author luwl
 * @version [1.0.0, 2020/7/28]
 */
@Slf4j
@DependsOn("corsFilter")
public class WebTokenAuthFilter extends TokenAuthParentFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws UnauthorizedException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        if (isLoginAttempt(request, response)) {
            Subject subject = getSubject(request, response);
            String token = httpServletRequest.getHeader(Constant.HEADER_TOKEN_NAME);
            WebToken webToken = new WebToken(token);
            httpServletRequest.setAttribute("authInfo", "none");
            try {
                subject.login(webToken);
            } catch (AuthenticationException e) {
                log.error(e.getMessage());
                httpServletRequest.setAttribute("authInfo", "loginExpire");
                return false;
            }
            return true;
        } else {
            httpServletRequest.setAttribute("authInfo", "unLogin");
            return false;
        }
    }

}
