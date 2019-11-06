import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import files.PayLoad;
import files.Resources;

public class c_Basics_Add_and_Delete {
	
	// GLOBAL VARIABLES
	Properties prop = new Properties();
	
	@BeforeTest
	public void setUp() throws IOException {
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\AAO8676\\Documents\\workspace-java-rest-api-automation\\DemoProject\\src\\files\\env.properties");
		prop.load(fis);
	}
	
	@Test
	public void AddAndDeletePlace() {
		/** ADD A PLACE **/
		// BaseURI or host		
		RestAssured.baseURI = prop.getProperty("HOST");
		
		// Get response
		Response resp = given().
				queryParam("key", prop.getProperty("KEY")).
				body(PayLoad.getPostData()).
				when().post(Resources.placePostData()).
				then().assertThat().
				statusCode(200).and().
				contentType(ContentType.JSON).and().
				body("status", equalTo("OK")).extract().response();
		
		String responseString = resp.asString();
		System.out.println(responseString);
		
		// Get place_id from JSON
		JsonPath jsp = new JsonPath(responseString);
		String place_id = jsp.get("place_id");
		System.out.println(place_id);
		
		/** DELETE A PLACE **/
		given().queryParam("key", prop.getProperty("KEY")).
		body("{\r\n" + 
				"	\"place_id\" : \""+ place_id +"\"\r\n" + 
				"}").
		when().post(Resources.deletePostData()).
		then().assertThat().
		statusCode(200).and().
		contentType(ContentType.JSON).and().
		body("status", equalTo("OK"));
		
	}

}
