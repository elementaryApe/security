package com.herman.security.app.exception;

/**
 * app认证异常
 * @author hsh
 * @create 2018-12-14 10:13
 **/
public class AppSecretException extends RuntimeException{
    private static final long serialVersionUID = -7611183030311346126L;

    public AppSecretException() {
    }

    public AppSecretException(String message) {
        super(message);
    }
}
