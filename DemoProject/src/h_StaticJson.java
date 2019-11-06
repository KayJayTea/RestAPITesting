import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class h_StaticJson {
	
	// GLOBAL VARIABLES
	Properties prop = new Properties();
	
	@BeforeTest
	public void setUp() throws IOException {
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\AAO8676\\Documents\\workspace-java-rest-api-automation\\DemoProject\\src\\files\\env.properties");
		prop.load(fis);
	}
	
	@Test
	public void AddBook() throws IOException {
				
		// BaseURI or host
		RestAssured.baseURI = prop.getProperty("HOST");
		
		Response addResp = given().
				header("Content-Type", "application/json").
				body(GenerateStringFromResource(
						"C:\\Users\\AAO8676\\Documents\\workspace-java-rest-api-automation\\DemoProject\\src\\files\\AddBookData.json")).
				when().post("/Library/Addbook.php").
				then().assertThat().
				statusCode(200).and().
				contentType(ContentType.JSON).
				extract().response();
		
		JsonPath jsp_1 = ReusableMethods.rawToJSON(addResp);
		System.out.println(jsp_1.getString("ID") + ": book is successfully added.");
	}
	
	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	
}
