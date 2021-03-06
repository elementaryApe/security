package com.herman.security.browser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.herman.security.core.support.SimpleResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 默认的退出成功处理器，如果设置了imooc.security.browser.signOutUrl，则跳到配置的地址上，
 * 如果没配置，则返回json格式的响应。
 *
 * @author hsh
 * @create 2018-11-30 15:06
 **/
public class HermanLogoutSuccessHandler implements LogoutSuccessHandler {

    private Logger logger=LoggerFactory.getLogger(getClass());

    private String singOutUrl;

    private ObjectMapper objectMapper=new ObjectMapper();

    public HermanLogoutSuccessHandler(String singOutUrl) {
        this.singOutUrl = singOutUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        logger.info("退出成功");
        if(StringUtils.isBlank(singOutUrl)){
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse("退出成功")));
        }else {
            httpServletResponse.sendRedirect(singOutUrl);
        }
    }
}
