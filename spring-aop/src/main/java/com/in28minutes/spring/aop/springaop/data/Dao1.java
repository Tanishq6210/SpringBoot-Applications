package com.in28minutes.spring.aop.springaop.data;

import org.springframework.stereotype.Repository;

import com.in28minutes.spring.aop.springaop.aspect.TrackTime;

/*
 * Because it will be communicating with the db
 */

@Repository
public class Dao1 {

	@TrackTime
	public String retrieveSomethig() {
		return "Dao1";
	}
}
