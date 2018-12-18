package com.herman.security.browser.session;

import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  默认的session失效处理策略
 *
 * @author hsh
 * @create 2018-11-30 10:54
 **/
public class HermanInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

    public HermanInvalidSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, ServletException {
        onSessionInvalid(httpServletRequest, httpServletResponse);
    }

}
