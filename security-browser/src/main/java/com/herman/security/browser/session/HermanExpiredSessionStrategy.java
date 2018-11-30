package com.herman.security.browser.session;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * session 失效处理
 *
 * @author hsh
 * @create 2018-11-30 10:08
 **/
public class HermanExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {

    public HermanExpiredSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    /**
     * session过期处理
     *
     * @param event session 并发超时时间
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        onSessionInvalid(event.getRequest(), event.getResponse());
    }

    @Override
    protected boolean isConcurrency() {
        return true;
    }
}
