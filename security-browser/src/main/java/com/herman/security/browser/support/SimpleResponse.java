package com.herman.security.browser.support;

/**
 * @author hsh
 * @create 2018-11-17 14:05
 **/
public class SimpleResponse {

    private Object content;

    public SimpleResponse(Object content) {
        this.content = content;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }
}
