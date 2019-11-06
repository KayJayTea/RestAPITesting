package com.restassured.examples.proxy;

import static io.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.ProxySpecification;
import io.restassured.specification.RequestSpecification;

public class ProxyExample {
	
	static RequestSpecBuilder builder;
	static RequestSpecification requestSpec;

	@BeforeClass
	public static void init() {
		/** Make sure Postman's Proxy server is running
		 * 1.) click the 'Capture requests and cookies' icon
		 * 2.) Enter port (e.g. 5555)
		 * 3.) Turn 'Capture requests' [ON]
		**/
		ProxySpecification ps = new ProxySpecification("localhost", 5555, "http");
		// RestAssured.proxy(ps);
		
		RestAssured.baseURI = "http://localhost:8080/student";
		
		builder = new RequestSpecBuilder();
		builder.setProxy(ps);
		
		requestSpec = builder.build();
	}
	
	@Test
	public void sendRequest() {
		
		given()
			.spec(requestSpec)
			.when()
			.get("/list")
			.then()
			.log()
			.body();
	}
}
