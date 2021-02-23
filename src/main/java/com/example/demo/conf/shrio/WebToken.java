package com.example.demo.conf.shrio;

import lombok.Data;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author youfang
 */
@Data
public class WebToken implements AuthenticationToken {

    private static final long serialVersionUID = 1282057025599826155L;

    private String token;

    public WebToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
