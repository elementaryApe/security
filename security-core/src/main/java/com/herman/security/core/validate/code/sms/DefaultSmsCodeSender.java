package com.herman.security.core.validate.code.sms;

/**
 * 默认短信验证码实现
 *
 * @author hsh
 * @create 2018-11-21 10:01
 **/
public class DefaultSmsCodeSender implements SmsCodeSender {

    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机"+mobile+"发送短信验证码"+code);
    }
}
