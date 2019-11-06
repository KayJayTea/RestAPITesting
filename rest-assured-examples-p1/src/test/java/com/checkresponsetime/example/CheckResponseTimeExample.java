package com.checkresponsetime.example;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class CheckResponseTimeExample {

	static final String API_KEY = "8ZPHAxbFRvyYPATX569FK1JAYTKWxhYE";
	static RequestSpecBuilder requestBuilder;
	static RequestSpecification requestSpec;
	static ResponseSpecBuilder responseBuilder;
	static ResponseSpecification responseSpec;
	
	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "https://api.nytimes.com";
		RestAssured.basePath = "/svc/books/v3";
		
		// REQUESTS
		requestBuilder  = new RequestSpecBuilder();
		requestBuilder.addQueryParam("api-key", API_KEY);		
		requestSpec = requestBuilder.build();
		
		// RESONSES
		responseBuilder = new ResponseSpecBuilder();
		responseBuilder.expectResponseTime(lessThan(5L), TimeUnit.SECONDS);
		responseSpec = responseBuilder.build();
	}
	
	@Test
	public void test001() {
		long responseTime = given()
		.spec(requestSpec)
		.log()
		.all()
		.when()
		.get("/lists/current/hardcover-fiction.json")
		.timeIn(TimeUnit.SECONDS);
		
		System.out.println("\nThe time taken is " + responseTime + " seconds");
		
		System.out.println("\nAssertion Time Test\n");
		
		given()
		.spec(requestSpec)
		.log()
		.all()
		.when()
		.get("/lists/current/hardcover-fiction.json")
		.then()
		.spec(responseSpec);
	}
}
