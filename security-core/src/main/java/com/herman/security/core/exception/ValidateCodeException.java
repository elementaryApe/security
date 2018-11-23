package com.herman.security.core.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常
 *
 * @author hsh
 * @create 2018-11-20 10:19
 **/
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -3595464551813993428L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
