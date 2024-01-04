package com.in28minutes.spring.aop.springaop.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPointConfig {

	/*
	 * What kind of method calls I would intercept execution(* PACKAGE.*.*(..)) This
	 * means * -> any return type PACKAGE (name of the package) .*(any class of that
	 * package).*(Any method of that class)(..(any number of parameter wala method))
	 * It intercepts all function calls
	 * 
	 * "execution(* com.in28minutes.spring.aop.springaop..(we can use ..) to
	 * intercept all function in all subpackages of springaop package)*.*(..))"
	 */
	@Pointcut("execution(* com.in28minutes.spring.aop.springaop.data.*.*(..))")
	public void dataLayerExecution() {
	}

	@Pointcut("execution(* com.in28minutes.spring.aop.springaop.business.*.*(..))")
	public void businessLayerExecution() {
	}

	// This configuration will intercept both data and business layer function
	@Pointcut("execution(* com.in28minutes.spring.aop.springaop.data.*.*(..)) && execution(* com.in28minutes.spring.aop.springaop.business.*.*(..))")
	public void allLayerExecution() {
	}

	// This will intercept any beans which are defined with this specific name
	@Pointcut("bean(*dao*)")
	public void beanContainingDao() {
	}

	// To intercepts all functions within the subpackage data
	@Pointcut("within(com.in28minutes.spring.aop.springaop.data..*)")
	public void dataLayerExecutionWithWithin() {
	}

	@Pointcut("@annotation(com.in28minutes.spring.aop.springaop.aspect.TrackTime)")
	public void trackTimeAnnotation() {
	}
}
