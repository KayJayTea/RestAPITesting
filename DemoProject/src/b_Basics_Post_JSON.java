import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.PayLoad;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class b_Basics_Post_JSON {
	
	// GLOBAL VARIABLES
	Properties prop = new Properties();
	
	@BeforeTest
	public void setUp() throws IOException {
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\AAO8676\\Documents\\workspace-java-rest-api-automation\\DemoProject\\src\\files\\env.properties");
		prop.load(fis);
	}
		
	@Test
	public void postData() {
		
		// BaseURI or host
		RestAssured.baseURI = prop.getProperty("HOST");
		
		given().queryParam("key", prop.getProperty("KEY")).
		body(PayLoad.getPostData()).
		when().post("/maps/api/place/add/json").
		then().assertThat().
		statusCode(200).and().
		contentType(ContentType.JSON).and().
		body("status", equalTo("OK"));
		
	}

}
