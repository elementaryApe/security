package com.herman.security.core.social.qq.connet;

import com.herman.security.core.social.qq.api.QQ;
import com.herman.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * QQServiceProvider
 *
 * @author hsh
 * @create 2018-11-23 17:58
 **/
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    private static final String URL_AUTHORIE="https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_ACCESS_TOKEN="https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId, appSecret, URL_AUTHORIE, URL_ACCESS_TOKEN));
        this.appId=appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
