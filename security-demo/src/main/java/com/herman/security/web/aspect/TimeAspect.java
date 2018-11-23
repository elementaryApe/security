package com.herman.security.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 切片类
 * @author hsh
 * @create 2018-11-16 11:10
 **/
//@Aspect
//@Component
public class TimeAspect {

    @Around("execution(* com.herman.security.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint point) throws Throwable {
        System.out.println(" timeAspect start");
        Object[] args = point.getArgs();
        for (Object arg:args) {
            System.out.println("arg:"+arg);
        }
        long start = new Date().getTime();
        Object proceed = point.proceed();
        System.out.println(" timeAspect end:"+(new Date().getTime()-start));
        return proceed;
    }

}
