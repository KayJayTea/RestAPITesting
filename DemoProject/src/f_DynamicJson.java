import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.PayLoad;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class f_DynamicJson {
	
	// GLOBAL VARIABLES
	Properties prop = new Properties();
	
	@BeforeTest
	public void setUp() throws IOException {
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\AAO8676\\Documents\\workspace-java-rest-api-automation\\DemoProject\\src\\files\\env.properties");
		prop.load(fis);
	}
	
	@Test
	public void addBook() throws IOException {
				
		// BaseURI or host
		RestAssured.baseURI = prop.getProperty("HOST");
		
		Response resp = given().
				header("Content-Type", "application/json").
				body(PayLoad.AddBook("Learn Appium Automation with Java", "478AAA", "111", "Kris Todd")).
				when().post("/Library/Addbook.php").
				then().assertThat().
				statusCode(200).and().
				contentType(ContentType.JSON).
				extract().response();
		
		JsonPath jsp = ReusableMethods.rawToJSON(resp);
		System.out.println(jsp.getString("ID"));

	}
	
}
