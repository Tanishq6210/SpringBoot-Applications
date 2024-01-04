package com.in28minutes.spring.aop.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

/*
 * AOP
 * Configuration
 */

/*
 * Aspect is what kind of method we are gonna accept + what advice will we give i.e. what logic will we write
 */
@Aspect
@Configuration
public class AfterAopAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * Here we are intercepting the value which the function returned, this only
	 * gets called if function executes successfully
	 */
	@AfterReturning(value = "com.in28minutes.spring.aop.springaop.aspect.CommonJoinPointConfig.businessLayerExecution()", returning = "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		logger.info("{} returned with value {} ", joinPoint, result);
	}

	/*
	 * Here we are intercepting, if there is any error while the method is called,
	 * this is called if there was an exception which function was getting executed
	 * 
	 */
	@AfterThrowing(value = "com.in28minutes.spring.aop.springaop.aspect.CommonJoinPointConfig.businessLayerExecution()", throwing = "exception")
	public void afterException(JoinPoint joinPoint, Exception exception) {

		logger.info("{} returned with value {} ", joinPoint, exception);
	}

	/*
	 * This is called in both the scenerios whether the function executes
	 * successfully or it throws any exception
	 */
	@After(value = "com.in28minutes.spring.aop.springaop.aspect.CommonJoinPointConfig.businessLayerExecution()")
	public void after(JoinPoint joinPoint) {

		logger.info("After execution {}", joinPoint);
	}
}
