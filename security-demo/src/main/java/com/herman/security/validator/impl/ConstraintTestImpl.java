package com.herman.security.validator.impl;

import com.herman.security.service.HelloService;
import com.herman.security.validator.ConstraintTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 自定义校验注解实现
 *
 * @author hsh
 * @create 2018-11-16 9:23
 **/
public class ConstraintTestImpl implements ConstraintValidator<ConstraintTest,Object> {
    private Logger logger= LoggerFactory.getLogger(getClass());

    @Resource
    private HelloService helloService;
    @Override
    public void initialize(ConstraintTest constraintTest) {
        logger.info("自定义校验注解 ConstraintTestImpl init");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        helloService.greeting("tom");
        logger.info("自定义校验注解 ConstraintTestImpl isValid:"+value);
        return false;
    }
}
