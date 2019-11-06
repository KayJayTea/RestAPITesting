import static io.restassured.RestAssured.given;

import java.io.File;
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


public class a_PostGetDeleteTweet {
	
	// GLOBAL VARIABLES
	Properties prop = new Properties();
	String post_id;

	
	@BeforeTest
	public void setUp() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ File.separator + "env.properties");
		prop.load(fis);
	}
	
	@Test(priority=1)
	public void postTweet() {
		
		RestAssured.baseURI = prop.getProperty("HOST");
		
		Response resp = given().auth().oauth(
				prop.getProperty("CONSUMER_KEY"), 
				prop.getProperty("CONSUMER_SECRET"),
				prop.getProperty("ACCESS_TOKEN"),
				prop.getProperty("TOKEN_SECRET")).
		queryParam("status",  "Testing twitter posts through automation").
		when().post(Resources.PostTweet()).
		then().assertThat().
		statusCode(200).and().
		contentType(ContentType.JSON).
		extract().response();
			
		JsonPath jsp = ReusableMethods.rawToJSON(resp);		
		post_id = jsp.getString("id").toString();
		System.out.println("TWEET ID: " + post_id + "\n");

	}

	@Test(priority=2)
	public void getLatestTweet() {
		RestAssured.baseURI = prop.getProperty("HOST");
		
		Response resp = given().auth().oauth(
				prop.getProperty("CONSUMER_KEY"), 
				prop.getProperty("CONSUMER_SECRET"),
				prop.getProperty("ACCESS_TOKEN"),
				prop.getProperty("TOKEN_SECRET")).
		queryParam("count",  "1").
		when().
		get(Resources.SearchUserTweets()).
		then().assertThat().
		statusCode(200).and().
		contentType(ContentType.JSON).
		extract().response();
		
		JsonPath jsp = ReusableMethods.rawToJSON(resp);
		System.out.println("TWEET: " + jsp.getString("text") + "\n");		
	}
	
	@Test(priority=3)
	public void deleteTweet() {
		
		RestAssured.baseURI = prop.getProperty("HOST");
		
		Response resp = given().auth().oauth(
				prop.getProperty("CONSUMER_KEY"), 
				prop.getProperty("CONSUMER_SECRET"),
				prop.getProperty("ACCESS_TOKEN"),
				prop.getProperty("TOKEN_SECRET")).
		when().
		post(Resources.DeleteTweetByID(post_id)).
		then().assertThat().
		statusCode(200).and().
		contentType(ContentType.JSON).
		extract().response();
		
		JsonPath jsp = ReusableMethods.rawToJSON(resp);		
		System.out.println("DELETED TWEET: " + jsp.getString("text") + "\n");
	}
}
