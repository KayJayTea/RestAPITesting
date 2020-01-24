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
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import placesAPI.Resources;
import test_framework.ReusableMethods;

public class d_Basics_Post_XML {
	
	private static Logger log = LogManager.getLogger(d_Basics_Post_XML.class.getName());
	
	// GLOBAL VARIABLES
	Properties prop = new Properties();
	
	@BeforeTest
	public void setUp() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\places_env.properties");
		prop.load(fis);
	}
	
	@Test
	public void Test() throws IOException {
		
		String post_data = GenerateStringFromResource(System.getProperty("user.dir") + "\\post_data.xml");
		
		// BaseURI or host
		RestAssured.baseURI = prop.getProperty("HOST");
		log.info("HOST (d_Basics_Post_XML): ");
		
		Response resp = given().
				queryParam("key", prop.getProperty("KEY")).
				body(post_data).
				when().post(Resources.placePostData_XML()).
				then().assertThat().
				statusCode(200).and().
				contentType(ContentType.XML).and().extract().response();
		
		XmlPath x = ReusableMethods.rawToXML(resp);
		log.info("Place ID: " + x.getString("response.place_id"));
	}
	
	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}

}
