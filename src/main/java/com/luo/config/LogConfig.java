package com.luo.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogConfig {

    @Pointcut(value = "execution(public * com.luo.controller.*.*(..))")
    public void logs() {
    }


    @Around(value = "logs()")
    public Object myLog(ProceedingJoinPoint pjp) {
        log.info("Target:{},Args:{},This:{}", pjp.getTarget(), pjp.getArgs(), pjp.getThis());

        Object o = null;

        try {
            o = pjp.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return o;
    }

}
