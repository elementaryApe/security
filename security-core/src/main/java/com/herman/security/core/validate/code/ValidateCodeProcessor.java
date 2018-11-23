package com.herman.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码处理器，封装不同的校验码的处理逻辑（模板接口）
 * Created by hsh on 18/11/21.
 */
public interface ValidateCodeProcessor {

    /**
     * 验证码放入session时的前缀
     */
   String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE";

    /**
     * 创建验证码
     */
    void create(ServletWebRequest request) throws Exception;

    /**
     * 校验验证码
     *
     * @param servletWebRequest
     * @throws Exception
     */
    void validate(ServletWebRequest servletWebRequest);


}
