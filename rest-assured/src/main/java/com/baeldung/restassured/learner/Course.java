package com.baeldung.restassured.learner;

public class Course {
	
	private String code;
	
	public Course() {
		
	}
	
	Course(String code) {
		this.code = code;
	}
	
	String getCode() {
		return code;
	}
}
