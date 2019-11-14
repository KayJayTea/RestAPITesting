package com.jsonpath.examples;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

public class SearchForJsonPath {
	
	static final String API_KEY = "8ZPHAxbFRvyYPATX569FK1JAYTKWxhYE";
	
	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "https://api.nytimes.com";
		RestAssured.basePath = "/svc/books/v3";
	}
	
	@Test
	// 1.) Extract number of results
	public void test001() {
		int numOfResults = given()
				.queryParam("api-key", API_KEY)
			.when()
				.get("/lists/current/hardcover-fiction.json")
			.then()
				.extract().path("num_results");
		
		System.out.println("------------------ Begin Test ------------------");
		System.out.println("Number of results: " + numOfResults);
		System.out.println("------------------- End Test -------------------\n");
	}
	
	@Test
	// 2.) Extract Previous Published Date
	public void test002() {
		String prevPubDate = given()
				.queryParam("api-key", API_KEY)
			.when()
				.get("/lists/current/hardcover-fiction.json")
			.then()
				.extract().path("results.previous_published_date");
		
		System.out.println("------------------ Begin Test ------------------");
		System.out.println("Previous Update Date: " + prevPubDate);
		System.out.println("------------------- End Test -------------------\n");
	}
	
	@Test
	// 3.) Extract Title of book [4]
	public void test003() {
		String title = given()
				.queryParam("api-key", API_KEY)
			.when()
				.get("/lists/current/hardcover-fiction.json")
			.then()
				.extract().path("results.books[4].title");
		
		System.out.println("------------------ Begin Test ------------------");
		System.out.println("Title of Book: " + title);
		System.out.println("------------------- End Test -------------------\n");
	}
	
	@Test
	// 4.) Extract Buy Links (HashMap)
	public void test004() {
		HashMap<String, String> buyLinks = given()
				.queryParam("api-key", API_KEY)
			.when()
				.get("/lists/current/hardcover-fiction.json")
			.then()
				.extract().path("results.books[4].buy_links[0]");
		
		System.out.println("------------------ Begin Test ------------------");
		System.out.println("Buy Links: " + buyLinks);
		System.out.println("------------------- End Test -------------------\n");
	}
	
	@Test
	// 5.) Print size of Array List Books
	public void test005() {
		int size = given()
				.queryParam("api-key", API_KEY)
			.when()
				.get("/lists/current/hardcover-fiction.json")
			.then()
				.extract().path("results.books.size()");
		
		System.out.println("------------------ Begin Test ------------------");
		System.out.println("Size of Array List (Books): " + size);
		System.out.println("------------------- End Test -------------------\n");
	}
	
	@Test
	// 6.) Extract all titles of books
	public void test006() {
		List<String> titles = given()
				.queryParam("api-key", API_KEY)
			.when()
				.get("/lists/current/hardcover-fiction.json")
			.then()
				.extract().path("results.books.title");
		
		System.out.println("------------------ Begin Test ------------------");
		System.out.println("All the Titles: " + titles);
		System.out.println("------------------- End Test -------------------\n");
	}
	
	@Test
	// 7.) Extract all books updated WEEKLY
	public void test007() {
		List<HashMap<String, Object>> values = given()
				.queryParam("api-key", API_KEY)
			.when()
				.get("/lists/names.json")
			.then()
				.extract().path("results.findAll{it.updated=='WEEKLY'}");
		
		System.out.println("------------------ Begin Test ------------------");
		System.out.println("List of values for results updated weekly: " + values);
		System.out.println("------------------- End Test -------------------\n");
	}
	
	@Test
	// 8.) Extract all "list_names" for books updated WEEKLY
	public void test008() {
		List<HashMap<String, Object>> values = given()
				.queryParam("api-key", API_KEY)
			.when()
				.get("/lists/names.json")
			.then()
				.extract().path("results.findAll{it.updated=='MONTHLY'}.display_name");
		
		System.out.println("------------------ Begin Test ------------------");
		System.out.println("Find all display names for results updated monthly: " + values);
		System.out.println("------------------- End Test -------------------\n");
	}
	
	@Test
	// 9.) Extract books with an 'image_width' < 322
	public void test009() {
		List<String> width = given()
				.queryParam("api-key", API_KEY)
			.when()
				.get("/lists/current/hardcover-fiction.json")
			.then()
				.extract().path("results.books.findAll{it.book_image_width<322}.title");
		
		System.out.println("------------------ Begin Test ------------------");
		System.out.println("Books with an image width of less than 322px: " + width);
		System.out.println("------------------- End Test -------------------\n");
	}
	
	@Test
	// 10.) Extract the oldest published date of results whose list_name begins with 'Paper'
	public void test010() {
		List<String> oldestPubDate = given()
				.queryParam("api-key", API_KEY)
			.when()
				.get("/lists/names.json")
			.then()
				.extract().path("results.findAll{it.list_name==~/Paper.*/}.oldest_published_date");
		
		System.out.println("------------------ Begin Test ------------------");
		System.out.println("Oldest Published Date(s) for list_names beginning with 'paper': " + oldestPubDate);
		System.out.println("------------------- End Test -------------------\n");
	}
	
	@Test
	// 11.) Extract the display name of results whose list_name begins ends with 'ice'
	public void test011() {
		List<String> displayName = given()
				.queryParam("api-key", API_KEY)
			.when()
				.get("/lists/names.json")
			.then()
				.extract().path("results.findAll{it.list_name==~/.*ice/}.display_name");
		
		System.out.println("------------------ Begin Test ------------------");
		System.out.println("Display Name(s) for list_names ending in 'ice': " + displayName);
		System.out.println("------------------- End Test -------------------\n");
	}
}
