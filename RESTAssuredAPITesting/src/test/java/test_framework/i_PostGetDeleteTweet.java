package test_framework;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import twitterAPI.Resources;
import test_framework.ReusableMethods;


public class i_PostGetDeleteTweet {
	
	// GLOBAL VARIABLES
	Properties prop = new Properties();
	String post_id;
	
	private static Logger log = LogManager.getLogger(i_PostGetDeleteTweet.class.getName());

	
	@BeforeTest
	public void setUp() throws IOException {
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\twitter_env.properties");
		prop.load(fis);
	}
	
	@Test(priority=1)
	public void postTweet() {
		
		RestAssured.baseURI = prop.getProperty("HOST");
		log.info("HOST (i_PostTweet): " + prop.getProperty("HOST"));
		
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
		log.info("TWEET ID: " + post_id + "\n");

	}

	@Test(priority=2)
	public void getLatestTweet() {
		RestAssured.baseURI = prop.getProperty("HOST");
		log.info("HOST (i_GetTweet): "+ prop.getProperty("HOST"));
		
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
		log.info("TWEET: " + jsp.getString("text") + "\n");		
	}
	
	@Test(priority=3)
	public void deleteTweet() {
		
		RestAssured.baseURI = prop.getProperty("HOST");
		log.info("HOST (i_DeleteTweet): "+ prop.getProperty("HOST"));
		
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
		log.info("DELETED TWEET: " + jsp.getString("text") + "\n");
	}
}
