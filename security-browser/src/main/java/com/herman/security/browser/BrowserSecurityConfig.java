package com.herman.security.browser;

import com.herman.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.herman.security.core.properties.SecurityConstants;
import com.herman.security.core.properties.SecurityProperties;
import com.herman.security.core.validate.code.base.ValidateCodeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * 认证信息配置
 *
 * @author hsh
 * @create 2018-11-17 9:25
 **/
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Autowired
    private ValidateCodeSecurityConfig validateCodeSecurityConfig;

    @Autowired
    protected AuthenticationSuccessHandler successHandler;

    @Autowired
    protected AuthenticationFailureHandler failureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);//第一次启动自动初始化数据库
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        new AbstractChannelSecurityConfig(successHandler,failureHandler).applyPasswordAuthenticationConfig(http);
        http.formLogin()//表单登录
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)//登录页面
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)//登录提交请求
                .successHandler(successHandler)//成功时
                .failureHandler(failureHandler)//失败时
                .and()
        .apply(validateCodeSecurityConfig)
                .and()
                .apply(smsCodeAuthenticationSecurityConfig)
                .and()
                .rememberMe()
                    .tokenRepository(persistentTokenRepository())
                    .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
                    .userDetailsService(userDetailsService)
                .and()
                    .authorizeRequests()//请求授权
                    .antMatchers(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL,//放行跳转登录页面访问
                            SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_MOBILE,
                            securityProperties.getBrowser().getLoginPage(),//放行登录页面访问
                            securityProperties.getBrowser().getAuthFailUrl(),//放行认证失败页面访问
                            SecurityConstants.DEFAULT_VALIDATE_CODE_URL_PREFIX + "/*"//放行图片验证码
                    ).permitAll()
                .anyRequest()//任何请求
                .authenticated()//都需要认证
                .and()
                .csrf().disable();//跨站信息防护
    }
}
