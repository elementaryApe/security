package com.herman.security.core.validate.code.sms;

/**
 * 短信发送接口
 *
 * @author hsh
 * @create 2018-11-21 10:00
 **/
public interface SmsCodeSender {

    void send(String mobile,String code);
}
