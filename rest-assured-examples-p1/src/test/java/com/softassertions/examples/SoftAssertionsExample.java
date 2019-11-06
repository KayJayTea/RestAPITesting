package com.softassertions.examples;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class SoftAssertionsExample {
	
	static final String API_KEY = "8ZPHAxbFRvyYPATX569FK1JAYTKWxhYE";
	
	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "https://api.nytimes.com";
		RestAssured.basePath = "/svc/books/v3";
	}
	
	
	@Test
	public void hardAsserts() {
		given()
			.queryParam("api-key", API_KEY)
			.when()
			.get("/lists/names.json")
			.then()
			.body("results.size()", equalTo(59))
			.body("results[0].list_name", equalTo("Combined Print and E-Book Fiction"))
			.body("results[0].display_name", equalTo("Combined Print & E-Book Fiction"))
			.body("results[0].list_name_encoded", equalTo("combined-print-and-e-book-fiction"))
			.body("results[0].oldest_published_date", equalTo("2011-02-13"))
			.body("results[0].newest_published_date", equalTo("2019-10-20"))
			.body("results[0].updated", equalTo("WEEKLY"))
			.statusCode(200);
	}
	
	@Test
	public void softAsserts() {
		given()
			.queryParam("api-key", API_KEY)
			.when()
			.get("/lists/names.json")
			.then()
			.body("results[0].list_name", equalTo("Combined Print and E-Book Fiction"),
					"results[0].display_name", equalTo("Combined Print & E-Book Fictionx"),
					"results[0].list_name_encoded", equalTo("combined-print-and-e-book-fiction"),
					"results[0].oldest_published_date", equalTo("2011-02-13"),
					"results[0].newest_published_date", equalTo("2019-10-20"),
					"results[0].updated", equalTo("WEEKLYx"))
			.statusCode(200);
	}

}
