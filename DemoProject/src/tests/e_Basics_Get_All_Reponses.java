package tests;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.Resources;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class e_Basics_Get_All_Reponses {
	
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
		
		Response resp = given().
				param("location", prop.getProperty("LOCATION")).
				param("radius", prop.getProperty("RADIUS")).
				param("key", prop.getProperty("KEY")).
				when().
				get(Resources.searchNearby()).
				then().assertThat().
				statusCode(200).and().
				contentType(ContentType.JSON).and().
//				body("results[0].name", equalTo("Sydney")).and().
//				body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
				header("Server", "scaffolding on HTTPServer2").
				extract().response();
		
		JsonPath jsp = ReusableMethods.rawToJSON(resp);
		int count = jsp.get("results.size()");
		
		System.out.println(count);
		
		for(int i = 0; i < count; i++) {
			System.out.println(jsp.getString("results["+ i +"].name"));
		}
		
		System.out.println("Test Complete");
	}

}
