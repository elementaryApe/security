package com.herman.security.service.impl;

import com.herman.security.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @author hsh
 * @create 2018-11-16 9:26
 **/
@Service
public class HelloServiceImpl implements HelloService {

    @Override
    public String greeting(String name) {
        System.out.println("greeting===>"+name);
        return " greeting"+"  "+name;
    }
}
