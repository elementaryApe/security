package com.herman.security.browser.validate.code.impl;

import com.herman.security.core.enums.ValidateCodeType;
import com.herman.security.core.validate.code.base.ValidateCode;
import com.herman.security.core.validate.code.base.ValidateCodeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成实现
 *
 * @author hsh
 * @create 2018-12-01 16:27
 **/
@Component
public class SessionValidateCodeRepository implements ValidateCodeRepository {

    /**
     * 验证码放入session时的前缀
     */
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

    /**
     * 操作session的工具类
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType codeType) {
        sessionStrategy.setAttribute(request,getSessionKey(codeType),code);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        return (ValidateCode) sessionStrategy.getAttribute(request,getSessionKey(validateCodeType));
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType codeType) {
        sessionStrategy.removeAttribute(request,getSessionKey(codeType));
    }


    /**
     * 构建验证码放入session时的key
     *
     */
    private String getSessionKey(ValidateCodeType validateCodeType) {
        return SESSION_KEY_PREFIX+validateCodeType.toString().toUpperCase();
    }


}
