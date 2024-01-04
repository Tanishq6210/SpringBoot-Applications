package com.in28minutes.spring.aop.springaop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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
public class UserAcessAspect {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	// What kind of method calls I would intercept
	// execution(* PACKAGE.*.*(..))
	// This means * -> any return type PACKAGE (name of the package) .*(any class of
	// that package).*(Any method of that class)(..(any number of parameter wala
	// method))
	// It intercepts all function calls

	/*
	 * This is called as point cut, which tells which kind of methods am I going to
	 * intercept
	 * 
	 * JoinPoint is a specific execution interception 100 baar method call honge to
	 * 100 joinPoint instance banenge
	 * 
	 * 
	 * Weaving -The process of implementing the AOP around the method calls is
	 * called weaving i.e. All this interception process should take place at the
	 * right moment when the function is getting called. This process is called
	 * weaving Weaver - The framework which implementing weaving is called weaver
	 */


	@Before("com.in28minutes.spring.aop.springaop.aspect.CommonJoinPointConfig.dataLayerExecution()")
	public void before(JoinPoint joinPoint) {
		/*
		 * After Interception what should we do, All the logic written inside this
		 * method is called ADVICE
		 */

		// For security reason we are doing access check here so that we don't have to
		// do it every time, we are doing it just once
		logger.info("Check for user access");
		logger.info(" Allowed execution for {}", joinPoint);
	}

}
