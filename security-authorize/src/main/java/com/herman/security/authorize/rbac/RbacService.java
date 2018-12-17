package com.herman.security.authorize.rbac;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * 授权模型接口
 * Created by hsh on 18/12/17.
 */
public interface RbacService {

    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
