package com.herman.security.authorize.rbac;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * @author hsh
 * @create 2018-12-17 17:53
 **/
@Component("rbacService")
public class RbacServiceImpl implements RbacService {

    private AntPathMatcher antPathMatcher =new AntPathMatcher();
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission=false;
        if(principal instanceof UserDetails ){
//            String username = ((UserDetails) principal).getUsername();
            //读取用户所拥有的所有URL
            Set<String> urls = new HashSet<>();
            urls.add("/user/*");
            for (String url:urls) {
                if(antPathMatcher.match(url,request.getRequestURI())){
                    hasPermission =true;
                    break;
                }
            }
        }
        return hasPermission;
    }
}
