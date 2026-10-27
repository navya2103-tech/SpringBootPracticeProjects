package com.example.mobile_recharge_application;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RechargeLoggingAspect {

    private static final Logger logger =
            LoggerFactory.getLogger(RechargeLoggingAspect.class);

    @Before("execution(* com.example.mobile_recharge_application.MobileRechargeController.*(..))")
    public void logBeforeControllerMethod(JoinPoint joinPoint) {
        logger.info("Controller method called: {}",
                joinPoint.getSignature().getName());
    }
}