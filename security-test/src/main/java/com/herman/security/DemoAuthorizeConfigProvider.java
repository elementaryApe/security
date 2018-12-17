package com.herman.security;

import com.herman.security.core.authorize.AuthorizeConfigProvider;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author hsh
 * @create 2018-12-17 17:18
 **/
@Component
@Order
public class DemoAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Override
    public boolean config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers("/user/*").hasRole("ADMIN")
        .antMatchers("/demo.html").hasRole("TEST")
        .anyRequest().access("@rbacService.hasPermission(request,authentication)");
        return true;
    }
}
