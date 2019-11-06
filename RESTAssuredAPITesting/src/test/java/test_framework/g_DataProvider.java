package test_framework;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import libraryAPI.PayLoad;
import libraryAPI.Resources;
import test_framework.ReusableMethods;

public class g_DataProvider {
	
	private static Logger log = LogManager.getLogger(g_DataProvider.class.getName());
	
	// GLOBAL VARIABLES
	Properties prop = new Properties();
	
	@BeforeTest
	public void setUp() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\library_env.properties");
		prop.load(fis);
	}
	
	@Test(dataProvider="BooksData")
	public void AddBook(String book, String isbn, String aisle, String author) throws IOException {
				
		// BaseURI or host
		RestAssured.baseURI = prop.getProperty("HOST");
		log.info("HOST (g_DataProvider): " + prop.getProperty("HOST"));
		
		Response addResp = given().
				header("Content-Type", "application/json").
				body(PayLoad.AddBook(book, isbn, aisle, author)).
				when().post(Resources.addBook()).
				then().assertThat().
				statusCode(200).and().
				contentType(ContentType.JSON).
				extract().response();
		
		JsonPath jsp_1 = ReusableMethods.rawToJSON(addResp);
		log.info(jsp_1.getString("ID") + ": book is successfully added.");
	}
	
	@Test(dataProvider="ID_No")
	public void DeleteBook(String isbn, String aisle) {
		// BaseURI or host
		RestAssured.baseURI = prop.getProperty("HOST");
		log.info("HOST (g_DataProvider): " + prop.getProperty("HOST"));
		
		Response delResp = given().
				header("Content-Type", "application/json").
				body(PayLoad.DeleteBook(isbn, aisle)).
				when().post(Resources.deleteBook()).
				then().assertThat().
				statusCode(200).and().
				contentType(ContentType.JSON).
				extract().response();
				
		JsonPath jsp = ReusableMethods.rawToJSON(delResp);
		log.info(isbn + aisle + ": " + jsp.getString("msg"));
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		 
		// Array = collection of elements
		// Multidimensional array = collection of arrays
		return new Object[][] {
			{"Automation Test Vol.1", "478AAA", "111", "Kris Todd"}, 
			{"Automation Test Vol.2", "658BBB", "222", "Kris Todd"}, 
			{"Automation Test Vol.3", "25478CCC", "333", "Kris Todd"}
		};
	}
	
	@DataProvider(name="ID_No")
	public Object[][] getID() {
		
		return new Object[][] {
			{"478AAA", "111"},
			{"658BBB", "222"},
			{"25478CCC", "333"}
		};
	}
	
}
