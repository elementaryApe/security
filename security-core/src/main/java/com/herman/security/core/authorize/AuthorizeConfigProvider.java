package com.herman.security.core.authorize;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * Created by hsh on 18/12/17.
 */
public interface AuthorizeConfigProvider {

    /**
     * 存在anyRequest 配置返回true
     * @param config
     * @return
     */
    boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);

}
