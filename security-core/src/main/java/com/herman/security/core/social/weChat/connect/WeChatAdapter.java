package com.herman.security.core.social.weChat.connect;

import com.herman.security.core.social.weChat.api.WeChat;
import com.herman.security.core.social.weChat.api.WeChatUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * 微信api接口适配器(配置微信用户适配到social)
 *
 * @author hsh
 * @create 2018-11-28 14:18
 **/
public class WeChatAdapter implements ApiAdapter<WeChat> {

    private String openId;

    public WeChatAdapter() {
    }

    public WeChatAdapter(String openId) {
        this.openId = openId;
    }

    @Override
    public boolean test(WeChat weChat) {
        return true;
    }

    @Override
    public void setConnectionValues(WeChat weChat, ConnectionValues connectionValues) {
        WeChatUserInfo userInfo = weChat.getUserInfo(openId);
        connectionValues.setProviderUserId(userInfo.getOpenid());
        connectionValues.setDisplayName(userInfo.getNickname());
        connectionValues.setImageUrl(userInfo.getHeadimgurl());
    }

    @Override
    public UserProfile fetchUserProfile(WeChat weChat) {
        return null;
    }

    @Override
    public void updateStatus(WeChat weChat, String s) {
        //do nothing
    }


}
