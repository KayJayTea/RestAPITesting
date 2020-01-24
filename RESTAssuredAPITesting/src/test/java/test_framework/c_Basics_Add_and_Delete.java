package test_framework;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import placesAPI.PayLoad;
import placesAPI.Resources;

public class c_Basics_Add_and_Delete {
	
	private static Logger log = LogManager.getLogger(c_Basics_Add_and_Delete.class.getName());
	
	// GLOBAL VARIABLES
	Properties prop = new Properties();
	
	@BeforeTest
	public void setUp() throws IOException {
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\places_env.properties");
		prop.load(fis);
	}
	
	@Test
	public void AddAndDeletePlace() {
		/** ADD A PLACE **/
		// BaseURI or host
		RestAssured.baseURI = prop.getProperty("HOST");
		log.info("HOST (c_Basics_Add_and_Delete): " + prop.getProperty("HOST"));
		
		// Get response
		log.info("Key used: " + prop.getProperty("KEY"));
		Response get_resp = given().
				queryParam("key", prop.getProperty("KEY")).
				body(PayLoad.getPostData()).
				when().post(Resources.placePostData_JSON()).
				then().assertThat().
				statusCode(200).and().
				contentType(ContentType.JSON).and().
				body("status", equalTo("OK")).extract().response();
		
		String get_responseString = get_resp.asString();
		log.info(get_responseString);
		
		// Get place_id from JSON
		JsonPath jsp = new JsonPath(get_responseString);
		String place_id = jsp.get("place_id");
		log.info("Place ID: " + place_id);
		
		/** DELETE A PLACE **/
		Response del_resp = given().queryParam("key", prop.getProperty("KEY")).
			body(PayLoad.postData_placeID(place_id)).
			when().post(Resources.deletePostData_JSON()).
			then().assertThat().
			statusCode(200).and().
			contentType(ContentType.JSON).and().
			body("status", equalTo("OK")).
			extract().response();
		
		String del_responseString = del_resp.asString();
		log.info(del_responseString);
		
	}

}
