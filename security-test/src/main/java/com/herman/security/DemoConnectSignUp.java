package com.herman.security;

import org.springframework.social.connect.Connection;

/**
 * 三方登录自定义注册逻辑(如果不实现则走默认注册页面)
 *
 * @author hsh
 * @create 2018-11-27 17:55
 **/
//@Component
public class DemoConnectSignUp /*implements ConnectionSignUp*/ {

//    @Override
    public String execute(Connection<?> connection) {
        //根据社交用户信息默认创建用户并返回用户唯一标识
        return connection.getDisplayName();
    }
}
