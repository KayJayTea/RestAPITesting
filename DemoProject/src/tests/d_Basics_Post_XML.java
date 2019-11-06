package tests;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import files.ReusableMethods;

public class d_Basics_Post_XML {
	
	// GLOBAL VARIABLES
	Properties prop = new Properties();
	
	@BeforeTest
	public void setUp() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\env.properties");
		prop.load(fis);
	}
	
	@Test
	public void Test() throws IOException {
		
		// BaseURI or host
		RestAssured.baseURI = prop.getProperty("HOST");
		
		Response resp = given().
				queryParam("key", prop.getProperty("KEY")).
				body(GenerateStringFromResource(System.getProperty("user.dir") + "\\post_data.xml")).
				when().post("/maps/api/place/add/xml").
				then().assertThat().
				statusCode(200).and().
				contentType(ContentType.XML).and().extract().response();
		
		XmlPath x = ReusableMethods.rawToXML(resp);
		System.out.println(x.getString("response.place_id"));
	}
	
	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}

}
