package com.herman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 标准测试项目启动类
 * @author hsh
 * @create 2018-12-17 20:39
 **/
@SpringBootApplication
@RestController
public class TestApplication {

    @GetMapping("/me")
    public Authentication getMe(Authentication authentication){
        return authentication;
    }
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class,args);
    }
}
