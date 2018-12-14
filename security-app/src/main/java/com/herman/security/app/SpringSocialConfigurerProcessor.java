package com.herman.security.app;

import com.herman.security.core.social.HermanSpringSocialConfigurer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;


/**
 * Bean
 * 初始化之前和初始化后都要经过这个类
 * 需要用户注册时不要跳转到之前浏览器情况下注册页面
 * 这里配置是将跳转到/social/signUp的服务上面
 * @author hsh
 * @create 2018-12-14 10:40
 **/
@Component
public class SpringSocialConfigurerProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (StringUtils.equals(beanName, "hermanSocialSecurityConfig")) {
            HermanSpringSocialConfigurer configurer = (HermanSpringSocialConfigurer)bean;
            configurer.signupUrl("/social/signUp");
            return configurer;
        }
        return bean;
    }
}
