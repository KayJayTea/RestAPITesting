package basic;

import static io.restassured.RestAssured.given;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

public class GETRequestDemo {

	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "https://maps.googleapis.com";
		RestAssured.basePath = "/maps/api";
	}
	
	@Test
	public void extractResponse() {
		given()
			.queryParam("units", "imperial")
			.queryParam("origins", "Washington,DC")
			.queryParam("destinations", "New+York+City,NY")
			.queryParam("key", "AIzaSyAVF4GOxN_u6-d22GDvWsWpYXpXvzG0tYE")
		.when()
			.get("/distancematrix/xml")
		.then()
			.statusCode(200);		
	}
}
