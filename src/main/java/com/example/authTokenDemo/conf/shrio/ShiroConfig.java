package com.example.authTokenDemo.conf.shrio;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author luwl
 * @version [1.0.0, 2020-4-20 下午 08:58]
 **/
@Configuration
public class ShiroConfig {

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //TODO 设置没有访问权限访问的url
//        shiroFilterFactoryBean.setUnauthorizedUrl("/sys/common/unauthorized");

        Map<String, Filter> filters = new HashMap<>();
        //token验证
        filters.put("wtaf", new WebTokenAuthFilter());
        //TODO 可以添加多个验证 角色权限(role),菜单权限(menu),接口权限(interface)
        shiroFilterFactoryBean.setFilters(filters);
        /** 不需要token就能访问的地址 **/
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        filterChainDefinitionMap.put("/swagger-ui.html", "anon");
        filterChainDefinitionMap.put("/webjars/**", "anon");
        filterChainDefinitionMap.put("/v2/**", "anon");
        filterChainDefinitionMap.put("/swagger-resources/**", "anon");

        filterChainDefinitionMap.put("/user/login", "anon");

        filterChainDefinitionMap.put("/**", "wtaf");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;

    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager();
        defaultSecurityManager.setRealm(customRealm());
        //停用shiro自带的session：Set-Cookie: JSESSIONID=xxx
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        defaultSecurityManager.setSubjectDAO(subjectDAO);
        //停用rememberMe: Set-Cookie: rememberMe=deleteMe; Path=/; Max-Age=0; Expires=Mon, 13-Jul-2020 16:07:18 GMT
        defaultSecurityManager.setRememberMeManager(null);
        return defaultSecurityManager;
    }

    @Bean
    public CustomRealm customRealm() {
        return new CustomRealm();
    }

    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 开启Shiro的注解
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
