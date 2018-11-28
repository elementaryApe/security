package com.herman.security.core.properties;

/**
 * social 配置类
 * @author hsh
 * @create 2018-11-26 11:47
 **/
public class SocialProperties {

    /**
     * 自定义拦截地址
     */
    private String filterProcessesUrl = "/auth";

    /**
     * qq配置
     */
    private QQProperties qq = new QQProperties();

    /**
     * 微信配置
     */
    private WeChatProperties weChat = new WeChatProperties();


    public WeChatProperties getWeChat() {
        return weChat;
    }

    public void setWeChat(WeChatProperties weChat) {
        this.weChat = weChat;
    }

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
