package com.herman.security.app.social.openId;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 封装登录信息
 * @author hsh
 * @create 2018-12-03 9:57
 **/
public class OpenIdAuthenticationToken extends AbstractAuthenticationToken {
    private static final long serialVersionUID = 4648004980253837725L;

    //OpenId
    private final Object principal;
    //服务提供商ID
    private String providerId;

    public OpenIdAuthenticationToken(Object principal, String providerId) {
        super(null);
        this.principal = principal;
        this.providerId = providerId;
        setAuthenticated(false);
    }

    public OpenIdAuthenticationToken(Object principal,Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }


    @Override
    public void setAuthenticated(boolean authenticated) {
        if(authenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
