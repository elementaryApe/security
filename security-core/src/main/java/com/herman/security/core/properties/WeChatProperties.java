package com.herman.security.core.properties;


import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * 微信配置
 * @author hsh
 * @create 2018-11-28 14:49
 **/
public class WeChatProperties extends SocialProperties {

    /**
     * 服务提供商Id
     */
    private String providerId="weChat";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
