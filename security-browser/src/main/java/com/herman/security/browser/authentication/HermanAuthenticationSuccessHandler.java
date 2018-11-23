package com.herman.security.browser.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.herman.security.core.enums.LoginType;
import com.herman.security.core.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功处理器
 *
 * @author hsh
 * @create 2018-11-19 11:03
 **/
//AuthenticationSuccessHandler 自定义实现接口
@Component
public class HermanAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //启动的时候springMVC会注册一个mapper
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功");
        logger.info("getName:" + authentication.getName());
        authentication.getAuthorities().forEach(coll -> {
            System.out.println("cl:" + coll.getAuthority());
        });
        logger.info("getDetails:" + authentication.getDetails());
        logger.info("getCredentials:" + authentication.getCredentials());
        logger.info("getPrincipal:" + authentication.getPrincipal());
        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            super.onAuthenticationSuccess(httpServletRequest, httpServletResponse, authentication);
        }

    }
}
