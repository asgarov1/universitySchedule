package com.asgarov.university.schedule.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    final static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.asgarov.university.schedule.service.*.*(..))")
    public void logMethodCall(JoinPoint jp) {
        String className = jp.getSignature().getDeclaringTypeName();
        String methodName = jp.getSignature().getName();
        logger.info("Executing: " + className + " method " + methodName);
    }

    @AfterReturning("execution(* com.asgarov.university.schedule.service.*.*(..))")
    public void logMethodCompletion(JoinPoint jp) {
        String className = jp.getSignature().getDeclaringType().getName();
        String methodName = jp.getSignature().getName();
        logger.info("Successfully executed: " + className + " method " + methodName);
    }
}
