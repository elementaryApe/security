package com.herman.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


/**
 * 自定义用户认证实现
 *
 * @author hsh
 * @create 2018-11-17 9:44
 **/
@Component
public class UserDetailsServiceTest implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        logger.info("开启用户自定义认证:" + userName);
        //根据用户信息判断是否被冻结
//        return new User(userName, "123456", AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
        return new User(
                userName, passwordEncoder.encode("123456"), true, true, true,
                true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
