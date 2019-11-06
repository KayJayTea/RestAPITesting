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

import googleAPI.Resources;

public class a_Basics_Get {
	
	private static Logger log = LogManager.getLogger(a_Basics_Get.class.getName());
	
	// GLOBAL VARIABLES
	Properties prop = new Properties();
	
	@BeforeTest
	public void setUp() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\google_apis.properties");
		prop.load(fis);
	}
	
	@Test
	public void Test() {
				
		// BaseURI or host
		RestAssured.baseURI = prop.getProperty("HOST");
		log.info("HOST (a_Basics_Get): " + prop.getProperty("HOST"));
		
		Response resp = given().
				param("location", prop.getProperty("LOCATION")).
				param("radius", prop.getProperty("RADIUS")).
				param("key", prop.getProperty("KEY")).
				when().
				get(Resources.searchNearby()).
				then().assertThat().
				statusCode(200).and().
				contentType(ContentType.JSON).and().
				body("results[0].name", equalTo("Hampton")).and().
				body("results[0].place_id", equalTo("ChIJ7ZVLmwGFuokRXXOEtSJCVkw")).and().
				header("Server", "scaffolding on HTTPServer2").
				extract().response();
		
		String responseString = resp.asString();
		System.out.println(responseString);
	}

}
