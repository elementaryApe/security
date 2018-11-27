package com.herman.security.core.properties;

import com.herman.security.core.enums.LoginType;

/**
 * 浏览器端配置
 * @author hsh
 * @create 2018-11-17 14:11
 **/
public class BrowserProperties {

    private String signUpUrl="/hermanSignUp.html";

    private String loginPage="/hermanLogin.html";

    private LoginType loginType= LoginType.JSON;

    private String authFailUrl="/hermanAuthenticationFail.html";

    private int rememberMeSeconds=3600;


    public String getLoginPage() {
        return loginPage;
    }

    public void setLoginPage(String loginPage) {
        this.loginPage = loginPage;
    }

    public LoginType getLoginType() {
        return loginType;
    }

    public void setLoginType(LoginType loginType) {
        this.loginType = loginType;
    }

    public String getAuthFailUrl() {
        return authFailUrl;
    }

    public void setAuthFailUrl(String authFailUrl) {
        this.authFailUrl = authFailUrl;
    }

    public int getRememberMeSeconds() {
        return rememberMeSeconds;
    }

    public void setRememberMeSeconds(int rememberMeSeconds) {
        this.rememberMeSeconds = rememberMeSeconds;
    }

    public String getSignUpUrl() {
        return signUpUrl;
    }

    public void setSignUpUrl(String signUpUrl) {
        this.signUpUrl = signUpUrl;
    }
}
