package com.in28minutes.spring.aop.springaop.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.in28minutes.spring.aop.springaop.data.Dao2;

/*
 * Since this is the web business layer class
 */

@Service
public class Business2 {

	@Autowired
	private Dao2 dao2;

	public String calculateSomething() {

		// Business Logic
		return dao2.retrieveSomethig();
	}
}
