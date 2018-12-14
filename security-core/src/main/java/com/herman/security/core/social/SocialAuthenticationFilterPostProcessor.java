package com.herman.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * @author hsh
 * @create 2018-12-13 17:01
 **/
public interface SocialAuthenticationFilterPostProcessor {

    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}
