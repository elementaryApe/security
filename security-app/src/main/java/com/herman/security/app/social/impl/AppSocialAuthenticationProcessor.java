package com.herman.security.app.social.impl;

import com.herman.security.core.social.SocialAuthenticationFilterPostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.stereotype.Component;

/**
 * @author hsh
 * @create 2018-12-13 17:08
 **/
@Component
public class AppSocialAuthenticationProcessor implements SocialAuthenticationFilterPostProcessor{

    @Autowired
    private AuthenticationSuccessHandler hermanAuthenticationSuccessHandler;

    @Override
    public void process(SocialAuthenticationFilter socialAuthenticationFilter) {
        socialAuthenticationFilter.setAuthenticationSuccessHandler(hermanAuthenticationSuccessHandler);
    }
}
