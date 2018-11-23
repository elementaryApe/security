package com.herman.security.core.validate.code.base;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成器
 *
 * @author hsh
 * @create 2018-11-20 14:29
 **/
public interface ValidateCodeGenerator {

    ValidateCode createCode(ServletWebRequest request);
}
