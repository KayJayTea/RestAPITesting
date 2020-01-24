package extractResponse;

import static io.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class GoogleExtractResponse {

	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "https://maps.googleapis.com";
		RestAssured.basePath = "/maps/api";
	}
	
	@Test
	public void extractResponse() {
		Response response = 
		given()
			.queryParam("units", "imperial")
			.queryParam("origins", "Washington,DC")
			.queryParam("destinations", "New+York+City,NY")
			.queryParam("key", "AIzaSyAVF4GOxN_u6-d22GDvWsWpYXpXvzG0tYE")
		.when()
			.get("/distancematrix/xml")
		.then()
			.statusCode(200)
			.extract().response();
		
		String responseString = response.asString();
		// System.out.println(responseString);
		
		String value = response.path("distancematrixresponse.row.element.duration.value");
		System.out.println("Duration Value is: " + value);
		
		XmlPath xmlPath = new XmlPath(responseString);
		String durationText = xmlPath.get("distancematrixresponse.row.element.duration.text");
		System.out.println("Duration Text is: " + durationText);
	}
}
