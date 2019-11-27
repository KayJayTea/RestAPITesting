package basics;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class ValidateResponse {

	final String API_KEY = "AIzaSyAVF4GOxN_u6-d22GDvWsWpYXpXvzG0tYE";
	
	@Before
	public void init() {
		RestAssured.baseURI = "https://maps.googleapis.com";
		RestAssured.basePath = "/maps/api";
	}
	
	@Test
	public void distanceVerification() {
			
		given()
			.param("units", "imperial")
			.param("origins", "Hapton,VA")
			.param("destinations", "Pittsburgh,PA")
			.param("key", API_KEY)
		.when()
			.get("/distancematrix/json")
		.then()
			.statusCode(200)
			.and()
			.body("rows[0].elements[0].distance.text", equalTo("414 mi"))
			.contentType(ContentType.JSON);
	}
}
