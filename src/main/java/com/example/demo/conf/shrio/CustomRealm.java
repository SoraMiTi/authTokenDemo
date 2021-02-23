package com.example.demo.conf.shrio;


import com.example.demo.framework.model.LoginUser;
import com.example.demo.framework.model.StaticInfo;
import com.example.demo.logic.service.UserTokenService;
import com.example.demo.util.SpringHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @author luwl
 * @version [1.0.0, 2020-4-20 下午 08:59]
 **/
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
        //授权必先登录，此处直接从StaticInfo中获取
        LoginUser loginUser = StaticInfo.getLoginUser();
        SimpleAuthorizationInfo authInfo = new SimpleAuthorizationInfo();
        return authInfo;
    }

    /**
     * 获取即将需要认证的信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        //why use SpringHelper get bean ?
        //because: 用autowired注入，会导致注入的类不受代理！
        UserTokenService userTokenService = SpringHelper.getBean(UserTokenService.class);
        LoginUser loginUser = userTokenService.getLoginUser(token);
        if (loginUser == null) {
            throw new AuthenticationException("token已经过期");
        }
        StaticInfo.setLoginUser(loginUser);
        return new SimpleAuthenticationInfo(token, token, getName());
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof WebToken;
    }

    /**
     * 密码验证器
     */
    @Override
    public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
        super.setCredentialsMatcher((token, info) -> true);
    }

}
