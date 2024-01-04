package com.in28minutes.spring.aop.springaop.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.spring.aop.springaop.aspect.TrackTime;
import com.in28minutes.spring.aop.springaop.data.Dao1;

/*
 * Since this is the web business layer class
 */

@Service
public class Business1 {

	@Autowired
	private Dao1 dao1;

	@TrackTime
	public String calculateSomething() {
		// Business Logic
		return dao1.retrieveSomethig();
	}
}
