package test_framework;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import libraryAPI.Resources;
import test_framework.ReusableMethods;

public class h_StaticJson {
	
	private static Logger log = LogManager.getLogger(h_StaticJson.class.getName());
	
	// GLOBAL VARIABLES
	Properties prop = new Properties();
	
	@BeforeTest
	public void setUp() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\library_env.properties");
		prop.load(fis);
	}
	
	@Test
	public void AddBook() throws IOException {
				
		// BaseURI or host
		RestAssured.baseURI = prop.getProperty("HOST");
		log.info("HOST (h_StaticJson): " + prop.getProperty("HOST"));
		
		Response addResp = given().
				header("Content-Type", "application/json").
				body(GenerateStringFromResource(System.getProperty("user.dir") + "\\AddBookData.json")).
				when().post(Resources.addBook()).
				then().assertThat().
				statusCode(200).and().
				contentType(ContentType.JSON).
				extract().response();
		
		JsonPath jsp_1 = ReusableMethods.rawToJSON(addResp);
		log.info(jsp_1.getString("ID") + ": book is successfully added.");
	}
	
	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	
}
