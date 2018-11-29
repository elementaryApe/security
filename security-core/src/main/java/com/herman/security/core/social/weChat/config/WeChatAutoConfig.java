package com.herman.security.core.social.weChat.config;

import com.herman.security.core.properties.SecurityProperties;
import com.herman.security.core.social.HermanConnectView;
import com.herman.security.core.social.weChat.connect.WeChatConnectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

/**
 * social集成微信整体配置
 *
 * @author hsh
 * @create 2018-11-28 14:46
 **/
@Configuration
@ConditionalOnProperty(prefix = "herman.security.social.weChat", name = "app-id")
public class WeChatAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        return new WeChatConnectFactory(securityProperties.getSocial().getWeChat().getProviderId(),
                securityProperties.getSocial().getWeChat().getAppId(),
                securityProperties.getSocial().getWeChat().getAppSecret());
    }

    /**
     * 配置微信绑定解绑视图
     * @return
     */
    @Bean({"connect/weChatConnect","connect/weChatConnected"})
    @ConditionalOnMissingBean(name = "weChatConnectedView")
    public View weChatConnectedView(){
        return new HermanConnectView();
    }

}
