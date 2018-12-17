package com.herman.security.core.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author hsh
 * @create 2018-12-17 16:41
 **/
@Component
public class HermanAuthorizeConfigManger implements AuthorizeConfigManger {

    @Autowired
    private List<AuthorizeConfigProvider> authorizeConfigProviders;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        authorizeConfigProviders.forEach(authorizeConfigProvider -> {
            authorizeConfigProvider.config(config);
        });
        config.anyRequest().authenticated();
    }
}
