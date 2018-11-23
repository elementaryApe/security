package com.herman.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 认证总配置
 * @author hsh
 * @create 2018-11-17 14:10
 **/
@ConfigurationProperties(prefix = "herman.security")
public class SecurityProperties {

    private BrowserProperties browser=new BrowserProperties();

    private ValidateCodeProperties code=new ValidateCodeProperties();

    public BrowserProperties getBrowser() {
        return browser;
    }

    public void setBrowser(BrowserProperties browser) {
        this.browser = browser;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }
}
