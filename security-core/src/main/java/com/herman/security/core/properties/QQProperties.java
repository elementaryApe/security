package com.herman.security.core.properties;

import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * QQ配置文件
 *
 * @author hsh
 * @create 2018-11-26 11:46
 **/
public class QQProperties extends SocialProperties {

    private String providerId="qq";

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }
}
