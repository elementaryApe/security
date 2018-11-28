package com.herman.security.core.social.weChat.connect;

import org.springframework.social.oauth2.AccessGrant;

/**
 * @author hsh
 * @create 2018-11-28 12:05
 **/
public class WeChatAccessGrant extends AccessGrant{
    private static final long serialVersionUID = 1008213457887007826L;

    private String openId;

    public WeChatAccessGrant() {
        super("");
    }

    public WeChatAccessGrant(String accessToken, String scope, String refreshToken, Long expiresIn) {
        super(accessToken, scope, refreshToken, expiresIn);
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
