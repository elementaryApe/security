package com.herman.security.core.properties;

/**
 * @author hsh
 * @create 2018-11-26 11:47
 **/
public class SocialProperties {

    private String filterProcessesUrl = "/auth";

    private QQProperties qq=new QQProperties();

    public QQProperties getQq() {
        return qq;
    }

    public void setQq(QQProperties qq) {
        this.qq = qq;
    }

    public String getFilterProcessesUrl() {
        return filterProcessesUrl;
    }

    public void setFilterProcessesUrl(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }
}
