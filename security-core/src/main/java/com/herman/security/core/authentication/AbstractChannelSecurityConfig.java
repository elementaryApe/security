package com.herman.security.core.authentication;

import com.herman.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author hsh
 * @create 2018-11-22 15:14
 **/
public class AbstractChannelSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    protected AuthenticationSuccessHandler hermanAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler hermanAuthenticationFailureHandler;


    public void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {

        http.formLogin()//表单登录
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)//登录页面
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)//登录提交请求
                .successHandler(hermanAuthenticationSuccessHandler)//成功时
                .failureHandler(hermanAuthenticationFailureHandler);//失败时
    }

    public AbstractChannelSecurityConfig(AuthenticationSuccessHandler hermanAuthenticationSuccessHandler,AuthenticationFailureHandler hermanAuthenticationFailureHandler) {
        this.hermanAuthenticationFailureHandler=hermanAuthenticationFailureHandler;
        this.hermanAuthenticationSuccessHandler=hermanAuthenticationSuccessHandler;
    }
}
