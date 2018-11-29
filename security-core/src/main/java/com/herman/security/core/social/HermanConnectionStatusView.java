package com.herman.security.core.social;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.social.connect.Connection;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 绑定查询视图信息
 *
 * @author hsh
 * @create 2018-11-29 14:34
 **/
@Component("connect/status")
public class HermanConnectionStatusView extends AbstractView {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void renderMergedOutputModel(Map<String, Object> map,
                                           HttpServletRequest httpServletRequest,
                                           HttpServletResponse httpServletResponse) throws Exception {
        Map<String, List<Connection<?>>> connections= (Map<String, List<Connection<?>>>) map.get("connectionMap");
        Map<String,Boolean> result=new HashMap<>();
        for (String key:connections.keySet()) {
            result.put(key,CollectionUtils.isNotEmpty(connections.get(key)));
        }
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
