package tests;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.Resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class a_Basics_Get {
	
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
		
		given().
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
				header("Server", "scaffolding on HTTPServer2");
		
		System.out.println("Test Complete");
	}

}
