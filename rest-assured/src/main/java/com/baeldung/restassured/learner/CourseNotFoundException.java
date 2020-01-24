package com.baeldung.restassured.learner;

@SuppressWarnings("serial")
public class CourseNotFoundException extends RuntimeException {

	CourseNotFoundException(String code) {
		super(code);
	}
}
