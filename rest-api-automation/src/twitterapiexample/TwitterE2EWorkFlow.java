package twitterapiexample;

import static io.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TwitterE2EWorkFlow {
	
	String consumerKey = "MlcBJLyNzgh1YjVbqY8vYU2Fp";
	String consumerSecret = "SwxRa657LAfbwTkOPDi5wcJoVfCRVPxrbiCfOqC6d5WhXhg27W";
	String accessToken = "303864833-ruWERl3CqF0VH2VQ2HfIAfh1SJVUBdjpIrlrsEla";
	String tokenSecret = "sqdw55waRgspcDFwqql8myKVrBxgzFLDrZXmyerkboN1m";
	static String tweetId = "";
	
	@BeforeClass
	public static void init() {
		RestAssured.baseURI = "https://api.twitter.com";
		RestAssured.basePath = "/1.1/statuses";
	}
	
	@Test
	public void test01_postTweet() {
		Response resp = 
		given()
			.auth()
			.oauth(consumerKey, consumerSecret, accessToken, tokenSecret)
			.queryParam("status", "My Tweet from Rest Assured API (testing).")
		.when()
			.post("/update.json")
		.then()
			.statusCode(200)
			.extract().response();
		
		tweetId = resp.path("id_str");
		System.out.println("Tweet ID: " + tweetId);
	}
	
	@Test
	public void test02_readTweet() {
		Response resp = 
		given()
			.auth()
			.oauth(consumerKey, consumerSecret, accessToken, tokenSecret)
			.queryParam("id", tweetId)
		.when()
			.get("/show.json")
		.then()
			.extract().response();
		
		String tweetText = resp.path("text");
		System.out.println("Tweet TEXT: " + tweetText);
	}
	
	@Test
	public void test03_deleteTweet() {
		given()
			.auth()
			.oauth(consumerKey, consumerSecret, accessToken, tokenSecret)
			.pathParam("id", tweetId)
		.when()
			.post("/destroy/{id}.json")
		.then()
			.statusCode(200);
	}
}
