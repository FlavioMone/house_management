package com.example.house_management.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class LoggingAspects {
	
	@Before(value = "execution(* com.example.house_management.controller.*.*(..))")
	public void beforeControllerMethodAdvice(JoinPoint jp) {
		log.info("Before executing method {} in rest controller {}", jp.getSignature().getName(), jp.getSignature().getDeclaringTypeName());
		log.debug("with arguments: {}", jp.getArgs());
	}
	
	@AfterReturning(pointcut = "execution(* com.example.house_management.controller.*.*(..))", returning = "returnValue")
	public void afterReturningControllerMethodAdvice(JoinPoint jp, Object returnValue) {
		log.info("After executing method {} in rest controller {}", jp.getSignature().getName(), jp.getSignature().getDeclaringTypeName());
		log.debug("with return value: {}", returnValue);
	}
	
	@Before(value = "execution(* com.example.house_management.service..*.*(..))")
	public void beforeServiceMethodAdvice(JoinPoint jp) {
		log.info("Before executing method {} in service {}", jp.getSignature().getName(), jp.getSignature().getDeclaringTypeName());
		log.debug("with arguments: {}", jp.getArgs());
	}
	
	@AfterReturning(pointcut = "execution(* com.example.house_management.service..*.*(..))", returning = "returnValue")
	public void afterReturningServiceMethodAdvice(JoinPoint jp, Object returnValue) {
		log.info("After executing method {} in service {}", jp.getSignature().getName(), jp.getSignature().getDeclaringTypeName());
		log.debug("with return value: {}", returnValue);
	}
	
	@AfterThrowing(
        pointcut="execution(* com.example.house_management.service..*.*(..))",
        throwing="ex")
    public void loggingErrors(RuntimeException ex) {
        log.error("Unhandled generic exception in service layer", ex);
    }

}
