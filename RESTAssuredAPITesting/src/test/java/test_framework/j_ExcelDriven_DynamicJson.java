/** NESTED HASHMAP EXAMPLE
		 ***** JSON *****
		   {
				"name" : "Learning Rest API Automation with Java, vol. I",
				"isbn" : "kjttjkAAA",
				"aisle" : "227",
				"author" : "John foe"
				"other" : {
					"year" : "1984",
					"publisher" : "John Smith Publishing"
				}
			}
		
		***** CODE *****		
		HashMap<String, Object> nested_map = new HashMap<String, Object>();
		map.put("year", "1984");
		map.put("publisher", "John Smith Publishing");
		map.put("other", nested_map);		
**/

package test_framework;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import libraryAPI.Resources;
import resources.DataDriven;

public class j_ExcelDriven_DynamicJson {
	
	// GLOBAL VARIABLES
	Properties prop = new Properties();
	
	@BeforeTest
	public void setUp() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\library_env.properties");
		prop.load(fis);
	}
	
	@Test
	public void addAndDeleteBook() throws IOException {
		
		DataDriven dd = new DataDriven();
		@SuppressWarnings("rawtypes")
		ArrayList data = dd.getData("RestAssured", "AddDeleteBook");
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("name", data.get(1));
		map.put("isbn", data.get(2));
		map.put("aisle", data.get(3));
		map.put("author", data.get(4));
		
		// BaseURI or host
		RestAssured.baseURI = prop.getProperty("HOST");
		
		Response resp = given().
				header("Content-Type", "application/json").
				body(map).
				when().post(Resources.addBook()).
				then().assertThat().
				statusCode(200).and().
				contentType(ContentType.JSON).
				extract().response();
		
		JsonPath jsp = ReusableMethods.rawToJSON(resp);
		System.out.println("Msg: " + jsp.getString("Msg"));
		System.out.println("ID: " + jsp.getString("ID"));
		
		String id = jsp.getString("ID");
		
		Response delResp = given().
				header("Content-Type", "application/json").
				body("{\r\n" + 
						"	\"ID\" : \"" + id + "\"\r\n" + 
						"}").
				when().post(Resources.deleteBook()).
				then().assertThat().
				statusCode(200).and().
				contentType(ContentType.JSON).
				extract().response();
				
		JsonPath jsp_2 = ReusableMethods.rawToJSON(delResp);
		System.out.println("\nMsg: " + jsp_2.getString("msg"));

	}
	
}
