package com.specification.examples;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecificationExample {
	
	static final String API_KEY = "8ZPHAxbFRvyYPATX569FK1JAYTKWxhYE";
	static RequestSpecBuilder builder;
	static RequestSpecification requestSpec;
	static ResponseSpecBuilder responseBuilder;
	static ResponseSpecification responseSpec;
	
	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "https://api.nytimes.com";
		RestAssured.basePath = "/svc/books/v3";
		
		// REQUEST Specifications
		builder  = new RequestSpecBuilder();
		builder.addQueryParam("api-key", API_KEY);
		
		requestSpec = builder.build();
		
		// RESPONSE Specifications
		responseBuilder = new ResponseSpecBuilder();
		responseBuilder.expectHeader("Content-Type", "application/json; charset=UTF-8");
		responseBuilder.expectHeader("Server", "Apache/2.2.15 (CentOS)");
		responseBuilder.expectStatusCode(200);
		
		responseBuilder.expectBody("status", equalTo("OK"));
		responseBuilder.expectBody("num_results", equalTo(15));
		responseBuilder.expectBody("results.list_name", equalTo("Hardcover Fiction"));
		responseBuilder.expectBody("results.books.title", hasItem("THE 19TH CHRISTMAS"));
		
		responseSpec = responseBuilder.build();
	}
	
	@Test
	public void test001() {
		given()
		.spec(requestSpec)
		
		
		.when()
		.get("/lists/current/hardcover-fiction.json")
		.then()
		.spec(responseSpec);
	}
	
	@Test
	public void test002() {
		given()
		.spec(requestSpec)
		
		
		.when()
		.get("/lists/current/hardcover-fiction.json")
		.then()
		.spec(responseSpec)
		.body("results.books.title", hasItem("THE DUTCH HOUSE"));
	}
}
