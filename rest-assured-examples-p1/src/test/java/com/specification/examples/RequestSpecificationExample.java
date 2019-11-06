package com.specification.examples;

import static io.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationExample {
	
	static final String API_KEY = "8ZPHAxbFRvyYPATX569FK1JAYTKWxhYE";
	static RequestSpecBuilder builder;
	static RequestSpecification requestSpec;
	
	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "https://api.nytimes.com";
		RestAssured.basePath = "/svc/books/v3";
		
		builder  = new RequestSpecBuilder();
		builder.addQueryParam("api-key", API_KEY);
		
		requestSpec = builder.build();
	}
	
	@Test
	public void test001() {
		given()
		.spec(requestSpec)
		.log()
		.all()
		.when()
		.get("/lists/current/hardcover-fiction.json")
		.then()
		.log()
		.all();
	}

}
