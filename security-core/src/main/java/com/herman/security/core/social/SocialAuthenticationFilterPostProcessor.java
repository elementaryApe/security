package com.herman.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;

/**
 * SocialAuthenticationFilter后处理器，用于在不同环境下个性化社交登录的配置
 *
 * @author hsh
 * @create 2018-12-13 17:01
 **/
public interface SocialAuthenticationFilterPostProcessor {

    void process(SocialAuthenticationFilter socialAuthenticationFilter);
}
