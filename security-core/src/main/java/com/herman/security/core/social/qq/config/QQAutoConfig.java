package com.herman.security.core.social.qq.config;

import com.herman.security.core.properties.SecurityProperties;
import com.herman.security.core.social.HermanConnectView;
import com.herman.security.core.social.qq.connet.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.web.servlet.View;

import javax.sql.DataSource;

/**
 * social集成qq整体配置
 * @author hsh
 * @create 2018-11-26 11:50
 **/
@Configuration
@ConditionalOnProperty(prefix = "herman.security.social.qq", name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        return new QQConnectionFactory(securityProperties.getSocial().getQq().getProviderId(),
                securityProperties.getSocial().getQq().getAppId(), securityProperties.getSocial().getQq().getAppSecret());
    }

    /**
     * 配置微信绑定解绑视图
     * @return
     */
    @Bean({"connect/qqConnect","connect/qqConnected"})
    @ConditionalOnMissingBean(name = "qqConnectedView")
    public View weChatConnectedView(){
        return new HermanConnectView();
    }

}
