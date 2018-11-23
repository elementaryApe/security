package com.herman.security.exception;

/**
 * 用户不存在自定义异常
 *
 * @author hsh
 * @create 2018-11-16 10:04
 **/
public class UserNotExistException extends RuntimeException {
    private static final long serialVersionUID = 8370441346350311183L;

    private String id;

    public UserNotExistException(String id){
        super(" user not exist");
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
