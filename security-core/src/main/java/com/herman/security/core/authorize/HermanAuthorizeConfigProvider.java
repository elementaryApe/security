package com.herman.security.core.authorize;

import com.herman.security.core.properties.SecurityConstants;
import com.herman.security.core.properties.SecurityProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @author hsh
 * @create 2018-12-17 16:35
 **/
@Component
@Order(Integer.MIN_VALUE)
public class HermanAuthorizeConfigProvider implements AuthorizeConfigProvider {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
        config.antMatchers(
                SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,//放行跳转登录页面访问
                SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                securityProperties.getBrowser().getLoginPage(),//放行登录页面访问
                securityProperties.getBrowser().getAuthFailUrl(),//放行认证失败页面访问
                SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*",//放行图片验证码
                securityProperties.getBrowser().getSignUpUrl(),//注册页面,
                "/user/register",//注册页面需要调优
                securityProperties.getBrowser().getSession().getSessionInvalidUrl(),//session失效时跳转的地址
                StringUtils.isNotEmpty(securityProperties.getBrowser().getSignOutUrl())?securityProperties.getBrowser().getSignOutUrl():"/signOut",//退出页面
                SecurityConstants.DEFAULT_GET_SOCIAL_USER_INFO//三方登录时获取第三方账号信息
        ).permitAll();
    }
}
