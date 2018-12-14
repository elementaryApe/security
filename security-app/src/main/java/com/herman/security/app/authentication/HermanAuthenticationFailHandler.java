package com.herman.security.app.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.herman.security.core.enums.LoginType;
import com.herman.security.core.exception.ValidateCodeException;
import com.herman.security.core.properties.SecurityProperties;
import com.herman.security.core.support.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 *
 * @author hsh
 * @create 2018-11-19 15:06
 **/
//也可以实现接口AuthenticationFailureHandler 自定义实现;
@Component
public class HermanAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    //启动的时候springMVC会注册一个mapper
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        AuthenticationException ex) throws IOException, ServletException {
        logger.info("登录失败");
        logger.info("失败信息：" + ex.getMessage());
        logger.info("getLocalizedMessage：" + ex.getLocalizedMessage());
        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(ex.getMessage())));
        } else {
            if (ex instanceof BadCredentialsException) {
                new SimpleUrlAuthenticationFailureHandler(securityProperties.getBrowser().getAuthFailUrl()).
                        onAuthenticationFailure(httpServletRequest, httpServletResponse, ex);
            } else if (ex instanceof ValidateCodeException) {
                new SimpleUrlAuthenticationFailureHandler(securityProperties.getBrowser().getAuthFailUrl()).
                        onAuthenticationFailure(httpServletRequest, httpServletResponse, ex);
            } else {
                super.onAuthenticationFailure(httpServletRequest, httpServletResponse, ex);
            }
        }

    }
}
