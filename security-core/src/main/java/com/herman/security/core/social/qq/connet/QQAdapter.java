package com.herman.security.core.social.qq.connet;

import com.herman.security.core.social.qq.api.QQ;
import com.herman.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * 配置qq用户适配到social
 * @author hsh
 * @create 2018-11-26 9:59
 **/
public class QQAdapter implements ApiAdapter<QQ> {

    @Override
    public boolean test(QQ qq) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ qqApi, ConnectionValues connectionValues) {
            QQUserInfo userInfo = qqApi.getUserInfo();
            connectionValues.setDisplayName(userInfo.getNickname());//用户名;
            connectionValues.setImageUrl(userInfo.getFigureurl_qq_1());//头像
            connectionValues.setProfileUrl(null);
            connectionValues.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    @Override
    public void updateStatus(QQ qq, String s) {
        // do nothing
    }
}
