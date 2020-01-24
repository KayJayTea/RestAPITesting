package com.xml.examples;

import static io.restassured.RestAssured.given;
import static io.restassured.path.xml.XmlPath.with;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.internal.path.xml.NodeChildrenImpl;

public class SearchXMLPathExamples {

	@Before
	public void init() {
		RestAssured.baseURI = "https://api.nytimes.com";
		RestAssured.basePath = "/svc/books/v3";
	}
	
	@Test
	public void test001() {
		String num_results = given()
			.queryParam("api-key", "8ZPHAxbFRvyYPATX569FK1JAYTKWxhYE")
		.when()
			.get("/lists/current/hardcover-fiction.xml")
		.then()
			// .log().body()
			.extract().path("result_set.num_results");
		
		System.out.println("--------------- Starting Test ---------------");
		System.out.println("Number of results: " + num_results);		
		System.out.println("---------------- End of Test ----------------");
	}
	
	@Test
	public void test002() {
		String title = given()
			.queryParam("api-key", "8ZPHAxbFRvyYPATX569FK1JAYTKWxhYE")
		.when()
			.get("/lists/current/hardcover-fiction.xml")
		.then()
			// .log().body()
			.extract().path("result_set.results.books.book[0].title");
		
		System.out.println("--------------- Starting Test ---------------");
		System.out.println("Title of book at index zero: " + title);		
		System.out.println("---------------- End of Test ----------------");
	}
	
	@Test
	public void test003() {
		String xml = given()
			.queryParam("api-key", "8ZPHAxbFRvyYPATX569FK1JAYTKWxhYE")
		.when()
			.get("/lists/current/hardcover-fiction.xml").asString();
		
		String buy_link = with(xml).getString("result_set.results.books.book[0].buy_links.buy_link.name");
		
		System.out.println("--------------- Starting Test ---------------");
		System.out.println("Buy Link: " + buy_link);		
		System.out.println("---------------- End of Test ----------------");
	}
	
	@Test
	public void test004() {
		NodeChildrenImpl val = given()
			.queryParam("api-key", "8ZPHAxbFRvyYPATX569FK1JAYTKWxhYE")
		.when()
			.get("/lists/current/hardcover-fiction.xml")
		.then()
			.extract()
			.path("result_set.results.books.book");
		
		System.out.println("--------------- Starting Test ---------------");
		System.out.println("The size of books index: " + val.size());
		System.out.println("---------------- End of Test ----------------");
	}
	
	@Test
	public void test005() {
		String xml = given()
			.queryParam("api-key", "8ZPHAxbFRvyYPATX569FK1JAYTKWxhYE")
		.when()
			.get("/lists/current/hardcover-fiction.xml").asString();
		
		List<String> titles = with(xml).getList("result_set.results.books.book.title");
		
		System.out.println("--------------- Starting Test ---------------");
		System.out.println("Titles: " + titles);		
		System.out.println("---------------- End of Test ----------------");
	}
	
	@Test
	public void test006() {
		String xml = given()
			.queryParam("api-key", "8ZPHAxbFRvyYPATX569FK1JAYTKWxhYE")
		.when()
			.get("/lists/current/hardcover-fiction.xml").asString();
		
		List<String> contributor = with(xml).getList("result_set.results.books.book.findAll{it.title=='THE SILENT PATIENT'}.contributor");
		
		System.out.println("--------------- Starting Test ---------------");
		System.out.println("Contributor: " + contributor);		
		System.out.println("---------------- End of Test ----------------");
	}
	
	@Test
	public void test007() {
		String xml = given()
			.queryParam("api-key", "8ZPHAxbFRvyYPATX569FK1JAYTKWxhYE")
		.when()
			.get("/lists/current/hardcover-fiction.xml").asString();
		
		List<String> publisher = with(xml).getList("**.findAll{it.name=='Amazon'}");
		
		System.out.println("--------------- Starting Test ---------------");
		System.out.println("Publisher: " + publisher);		
		System.out.println("---------------- End of Test ----------------");
	}
}
