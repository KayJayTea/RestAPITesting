package basics;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostRequestDemo {
	
	final String API_KEY = "qaclick123";
	
	@Before
	public void init() {
		RestAssured.baseURI = "http://216.10.245.166";
		RestAssured.basePath = "/maps/api/place";
	}
	
	@Test
	public void addPlace() {
		given()
			.queryParam("key", API_KEY)
			.body("{\r\n" + 
					"	\"location\": {\r\n" + 
					"		\"lat\" : -38.383494,\r\n" + 
					"		\"lng\" : 33.427362\r\n" + 
					"	},\r\n" + 
					"	\r\n" + 
					"	\"accuracy\" : 50,\r\n" + 
					"	\"name\" : \"Frontline House\",\r\n" + 
					"	\"phone_number\" : \"(+1) 757-846-6162\",\r\n" + 
					"	\"address\" : \"1234 Main Street, Hampton, VA, 23664\",\r\n" + 
					"	\"types\" : [\"Shoe Park\", \"Shop\"],\r\n" + 
					"	\"website\" : \"http://google.com\",\r\n" + 
					"	\"language\" : \"French-IN\"\r\n" + 
					"}")
		.when()
			.post("/add/json")
		.then()
			.statusCode(200)
			.and()
			.contentType(ContentType.JSON)
			.body("scope", equalTo("APP")).and()
			.body("status", equalTo("OK"));
	}
}
