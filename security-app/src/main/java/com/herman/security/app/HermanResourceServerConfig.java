package com.herman.security.app;

import com.herman.security.app.social.openId.OpenIdAuthenticationSecurityConfig;
import com.herman.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.herman.security.core.authorize.AuthorizeConfigManger;
import com.herman.security.core.properties.SecurityConstants;
import com.herman.security.core.properties.SecurityProperties;
import com.herman.security.core.validate.code.base.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 服务商资源服务器配置
 *
 * @author hsh
 * @create 2018-12-01 10:29
 **/
@Configuration
@EnableResourceServer
public class HermanResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private AuthenticationSuccessHandler hermanAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler hermanAuthenticationFailureHandler;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private SpringSocialConfigurer hermanSocialSecurityConfig;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

    @Autowired
    private AuthorizeConfigManger authorizeConfigManger;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()//表单登录
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)//登录页面
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)//登录提交请求
                .successHandler(hermanAuthenticationSuccessHandler)//成功时
                .failureHandler(hermanAuthenticationFailureHandler);//失败时
        http.apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .apply(hermanSocialSecurityConfig)
                .and()
                .apply(openIdAuthenticationSecurityConfig)
                .and()
                .csrf().disable();//跨站信息防护
        authorizeConfigManger.config(http.authorizeRequests());
    }
}
