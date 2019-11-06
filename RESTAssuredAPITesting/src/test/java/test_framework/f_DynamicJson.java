package test_framework;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import libraryAPI.Resources;
import libraryAPI.PayLoad;
import test_framework.ReusableMethods;

public class f_DynamicJson {
	
	// GLOBAL VARIABLES
	Properties prop = new Properties();
	
	@BeforeTest
	public void setUp() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\library_env.properties");
		prop.load(fis);
	}
	
	@Test
	public void addBook() throws IOException {
				
		// BaseURI or host
		RestAssured.baseURI = prop.getProperty("HOST");
		
		Response resp = given().
				header("Content-Type", "application/json").
				body(PayLoad.AddBook("Learn Appium Automation with Java", "77777DDD", "999", "Kris Todd")).
				when().post(Resources.addBook()).
				then().assertThat().
				statusCode(200).and().
				contentType(ContentType.JSON).
				extract().response();
		
		JsonPath jsp = ReusableMethods.rawToJSON(resp);
		System.out.println(jsp.getString("ID"));

	}
	
}
