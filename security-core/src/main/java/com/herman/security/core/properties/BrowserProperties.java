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

    /**
     * 退出登录返回地址
     */
    private String signOutUrl;

    private LoginType loginType= LoginType.JSON;

    private String authFailUrl="/hermanAuthenticationFail.html";

    private int rememberMeSeconds=3600;

    /**
     * 退出请求地址
     */
    private String signOut="/signOut";

    private String deleteCookies="JSESSIONID";

    private SessionProperties session=new SessionProperties();

    public SessionProperties getSession() {
        return session;
    }

    public void setSession(SessionProperties session) {
        this.session = session;
    }

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

    public String getSignOutUrl() {
        return signOutUrl;
    }

    public void setSignOutUrl(String signOutUrl) {
        this.signOutUrl = signOutUrl;
    }

    public String getSignOut() {
        return signOut;
    }

    public void setSignOut(String signOut) {
        this.signOut = signOut;
    }

    public String getDeleteCookies() {
        return deleteCookies;
    }

    public void setDeleteCookies(String deleteCookies) {
        this.deleteCookies = deleteCookies;
    }
}
