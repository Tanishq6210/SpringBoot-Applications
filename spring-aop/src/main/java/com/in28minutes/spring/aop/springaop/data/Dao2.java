package com.in28minutes.spring.aop.springaop.data
;

import org.springframework.stereotype.Repository;

/*
 * Because it will be communicating with the db
 */

@Repository
public class Dao2 {
	public String retrieveSomethig() {
		return "Dao1";
	}
}
