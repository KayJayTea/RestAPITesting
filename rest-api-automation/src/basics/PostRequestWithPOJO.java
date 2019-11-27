package basics;

import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import models.PlacesAddModel;

public class PostRequestWithPOJO {
	
	final String API_KEY = "qaclick123";
	
	@Before
	public void init() {
		RestAssured.baseURI = "http://216.10.245.166";
		RestAssured.basePath = "/maps/api/place";
	}
	
	@Test
	public void addPlace() {
		Map<String, Double> locationMap = new HashMap<String, Double>();
		locationMap.put("lat", -38.383494);
		locationMap.put("lng", 33.427362);
		
		List<String> types = new ArrayList<String>();
		types.add("Shoe Park");
		types.add("Shop");
		
		PlacesAddModel places = new PlacesAddModel();
		places.setLocation(locationMap);
		places.setAccuracy(50);
		places.setName("Frontline House");
		places.setPhone_number("(+1) 757-846-6162");
		places.setAddress("1234 Main Street, Hampton, VA, 23664");
		places.setTypes(types);
		places.setWebsite("http://www.google.com");
		places.setLanguage("French-IN");
		
		// Response resp = 
		given()
			.queryParam("key", API_KEY)
			.body(places)
		.when()
			.post("/add/json")
		// System.out.println(resp.body().prettyPrint());
		.then()
			.statusCode(200).and()
			.contentType(ContentType.JSON)
			.body("scope", equalTo("APP")).and()
			.body("status", equalTo("OK"));
	}
}
