package com.herman.security.core.social.weChat.connect;

import com.herman.security.core.social.weChat.api.WeChat;
import com.herman.security.core.social.weChat.api.WeChatImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;

/**
 * 微信服务提供商
 * provider 是social对服务商的一个整体封装包含两部分：OAuth2Template、Api
 * 根据OAuth协议规范:资源所有者对第三方应用发起访问时，第三方应用将资源所有者导入到服务提供商（认证服务器），资源所有者同意授权后，
 * 服务提供商返回授权码给第三方应用，第三方应用根据授权码向服务提供商申请令牌并在获取令牌后
 * 通过访问资源所有者在服务提供商（资源服务器）中的唯一标识（openId）获取资源数据（请求对应开放的api）。
 * 所以 ：
 * oauth2Operations（OAuth2Template） 要连接认证服务器，获取授权码，并通过授权码获取令牌
 * Api（AbstractOAuth2ApiBinding）通过令牌获取openId并获取对应的API资源数据
 *
 * 注意微信中不需要获取通过accessToken获取openId，在获取accessToken的接口中已经将openId返回
 *
 *
 * @author hsh
 * @create 2018-11-28 11:04
 **/
public class WeChatServiceProvider extends AbstractOAuth2ServiceProvider<WeChat> {

    /**
     * 微信获取授权码的url
     */
    private static final String URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";

    /**
     * 根据授权码获取令牌地址url
     */
    private static final String URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    public WeChatServiceProvider(String appId, String secret) {
        super(new WeChatOAuth2Template(appId, secret, URL_AUTHORIZE, URL_ACCESS_TOKEN));
    }

    @Override
    public WeChat getApi(String accessToken) {
        return new WeChatImpl(accessToken);
    }
}
