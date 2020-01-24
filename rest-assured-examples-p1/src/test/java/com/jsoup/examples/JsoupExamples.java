package com.jsoup.examples;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.filter.session.SessionFilter;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JsoupExamples {

	public static SessionFilter filter;

	@BeforeClass
	public static void init() {
		filter = new SessionFilter();
		RestAssured.baseURI = "http://localhost:9090";

		RestAssured.given()
			.auth()
			.form("user", "user", new FormAuthConfig("/login", "uname", "pwd"))
			.filter(filter)
			.get();
	}

	@Test
	// @Ignore
	public void test001_extractTitle() {
		String response = RestAssured.given().when().get("http://localhost:9090/").asString();

		Document document = Jsoup.parse(response);

		System.out.println("Title of page is: " + document.title().toUpperCase());
	}

	@Test
	@Ignore
	public void test002_extractElementsByTag() {
		String response = RestAssured.given().when().get("http://localhost:9090/").asString();
		Document document = Jsoup.parse(response);

		Elements element = document.getElementsByTag("form");
		System.out.println("The number of form elements is: " + element.size());

//		 for(Element e : element) {
//			 System.out.println(e);
//		 }
	}

	@Test
	@Ignore
	public void test003_extractElementsById() {
		String response = RestAssured.given().when().get("http://localhost:9090/").asString();
		Document document = Jsoup.parse(response);

		Element element = document.getElementById("command");

		System.out.println("The Element contents are: " + element.text());

	}

	@Test
	@Ignore
	public void test004_extractLinks() {
		String response = RestAssured.given().when().get("http://localhost:9090/").asString();
		Document document = Jsoup.parse(response);

		Elements links = document.select("a[href]");

		for (Element e : links) {
			System.out.println(e.text());
		}
	}

	@Test
	// @Ignore
	public void test005_extractEmailFromStudentList() {
		String response = RestAssured.given().sessionId(filter.getSessionId()).when().get("/student/list").asString();
		Document document = Jsoup.parse(response);
		
		Elements emailId = document.select("table tbody tr td:nth-child(4)");
		System.out.println(emailId.size() + "\n");		
		
		ArrayList<String> actualVal = new ArrayList<String>();
		for(Element e : emailId) {
			actualVal.add(e.text());
		}
		
		assertThat(actualVal, hasItem("scelerisque.sed@senectuset.org"));
	}
}
