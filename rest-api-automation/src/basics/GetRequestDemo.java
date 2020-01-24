package basics;

import static io.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetRequestDemo {
	
	final String API_KEY = "AIzaSyAVF4GOxN_u6-d22GDvWsWpYXpXvzG0tYE";
	
	@Before
	public void init() {
		RestAssured.baseURI = "https://maps.googleapis.com";
		RestAssured.basePath = "/maps/api";
	}
	
	@Test
	@Ignore
	public void statusCodeVerification() {
			
		given()
			.param("units", "imperial")
			.param("origins", "Hapton,VA")
			.param("destinations", "Pittsburgh,PA")
			.param("key", API_KEY)
		.when()
			.get("/distancematrix/json")
		.then()
			.statusCode(200);
	}
	
	@Test
	public void getResponseBody() {
			
		Response resp =
		given()
			.param("units", "imperial")
			.param("origins", "Hapton,VA")
			.param("destinations", "Pittsburgh,PA")
			.param("key", API_KEY)
		.when()
			.get("/distancematrix/json");
		
		System.out.println(resp.body().prettyPrint());
	}
}
