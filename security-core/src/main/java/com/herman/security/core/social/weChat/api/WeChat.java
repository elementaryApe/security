package com.herman.security.core.social.weChat.api;

/**
 * 微信Api接口
 *
 * @author hsh
 * @create 2018-11-28 10:38
 **/
public interface WeChat {

    WeChatUserInfo getUserInfo(String openId);
}
