package com.in28minutes.spring.aop.springaop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
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
public class MethodExecutionCalculationAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * This method allows to do something around the function executes. We can do
	 * something before the function starts, then continue the function with
	 * .proceed then after the completion of function we can do something
	 */

	// Instead of tracking every method in the business layer, we would be tracking
	// only those method which have the @trackTime annotation defined on it
	@Around("com.in28minutes.spring.aop.springaop.aspect.CommonJoinPointConfig.trackTimeAnnotation()")
	public void around(ProceedingJoinPoint joinPoint) throws Throwable {
		/*
		 * startTime = x; allow execution of method endTime = y;
		 */
		long startTime = System.currentTimeMillis();
		// This .proceed() method is called to continue the execution of the function
		joinPoint.proceed();
		long timeTaken = System.currentTimeMillis() - startTime;
		logger.info("Time taken by execution of {} is {} ", joinPoint, timeTaken);
	}

}
