package com.herman.security.core.social.weChat.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 微信接口实现
 *
 * @author hsh
 * @create 2018-11-28 10:44
 **/
public class WeChatImpl extends AbstractOAuth2ApiBinding implements WeChat {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private ObjectMapper objectMapper = new ObjectMapper();

    private String accessToken;

    /**
     * 获取用户信息的url
     */
    private static final String URL_GET_USER_INFO = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";

    public WeChatImpl(String accessToken) {
        super(accessToken,TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.accessToken=accessToken;
    }

    /**
     * 默认注册的StringHttpMessageConverter字符集为ISO-8859-1，而微信返回的是UTF-8的，所以覆盖了原来的方法。
     */
    @Override
    protected List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> messageConverters = super.getMessageConverters();
        messageConverters.remove(0);
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return messageConverters;
    }


    @Override
    public WeChatUserInfo getUserInfo(String openId) {
        String url = String.format(URL_GET_USER_INFO, accessToken, openId);
        logger.info("获取微信返回用户信息URL:"+url);
        String response = getRestTemplate().getForObject(url, String.class);
        logger.info("获取微信返回用户信息:"+response);
        if(StringUtils.contains(response, "errcode")) {
            return null;
        }
        WeChatUserInfo profile = null;
        try {
            profile = objectMapper.readValue(response, WeChatUserInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profile;
    }
}
