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
import io.restassured.response.Response;
import placesAPI.PayLoad;
import placesAPI.Resources;

public class b_Basics_Post_JSON {
	
	private static Logger log = LogManager.getLogger(b_Basics_Post_JSON.class.getName());
	
	// GLOBAL VARIABLES
	Properties prop = new Properties();
	
	@BeforeTest
	public void setUp() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\places_env.properties");
		prop.load(fis);
	}
		
	@Test
	public void postData() {
		
		// BaseURI or host
		RestAssured.baseURI = prop.getProperty("HOST");		
		log.info("HOST (b_Basics_Post_JSON): " + prop.getProperty("HOST"));
		
		Response resp = 
				given().queryParam("key", prop.getProperty("KEY")).
				body(PayLoad.getPostData()).
				when().post(Resources.placePostData_JSON()).
				then().assertThat().
				statusCode(200).and().
				contentType(ContentType.JSON).and().
				body("status", equalTo("OK")).
				extract().response();
		
		String responseString = resp.asString();
		log.info(responseString);
	}

}
