package com.herman.security.core.social;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 绑定视图信息
 *
 * @author hsh
 * @create 2018-11-29 14:34
 **/
public class HermanConnectView extends AbstractView {

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map,
                                           HttpServletRequest httpServletRequest,
                                           HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.setContentType(MediaType.TEXT_HTML_VALUE);
        if (map.get("connections") == null)
            httpServletResponse.getWriter().write("<h3>unbindSuccess</h3>");
        else
            httpServletResponse.getWriter().write("<h3>bindingSuccess</h3>");
    }
}
