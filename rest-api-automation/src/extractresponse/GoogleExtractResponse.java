package extractresponse;

import static io.restassured.RestAssured.given;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class GoogleExtractResponse {

	final String API_KEY = "AIzaSyAVF4GOxN_u6-d22GDvWsWpYXpXvzG0tYE";
	
	@Before
	public void init() {
		RestAssured.baseURI = "https://maps.googleapis.com";
		RestAssured.basePath = "/maps/api";
	}
	
	@Test
	public void extractResoponse() {
		Response resp = 
		given()
			.queryParam("units", "imperial")
			.queryParam("origins", "Hapton,VA")
			.queryParam("destinations", "Pittsburgh,PA")
			.queryParam("key", API_KEY)
		.when()
			.get("/distancematrix/xml")
		.then()
			.statusCode(200)
			.extract().response();
		
		String responseString = resp.asString();
//		System.out.println(responseString);
		
		String value = resp.path("distancematrixresponse.row.element.duration.value");
		System.out.println("Duration Value: " + value);
		
		XmlPath xmlPath = new XmlPath(responseString);
		String textPath = xmlPath.get("distancematrixresponse.row.element.duration.text");
		System.out.println("The Duration text using XMLPath is: " + textPath);
		
		Assert.assertEquals(value, "22980");
		Assert.assertEquals(textPath, "6 hours 23 mins");
	}
	
}
