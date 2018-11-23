package com.herman.security.core.properties;

/**
 * 验证码配置
 *
 * @author hsh
 * @create 2018-11-20 11:36
 **/
public class ImageCodeProperties extends SmsCodeProperties {

    public ImageCodeProperties() {
        setLength(4);
    }

    private int width=67;
    private int height=23;

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
