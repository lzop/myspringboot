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
    public Object myLog(ProceedingJoinPoint pjp) throws Throwable {
        log.info("请求目的方法:{}.{}", pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName());
        log.info("请求参数:{}", pjp.getArgs());
        return pjp.proceed();
    }

}
