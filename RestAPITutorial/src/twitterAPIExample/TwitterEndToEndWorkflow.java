package twitterAPIExample;

import static io.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import io.restassured.RestAssured;
import io.restassured.response.Response;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TwitterEndToEndWorkflow {
	
	String consumerKey = "MlcBJLyNzgh1YjVbqY8vYU2Fp";
	String consumerSecret = "SwxRa657LAfbwTkOPDi5wcJoVfCRVPxrbiCfOqC6d5WhXhg27W";
	String accessToken = "303864833-ruWERl3CqF0VH2VQ2HfIAfh1SJVUBdjpIrlrsEla";
	String accessSecret = "sqdw55waRgspcDFwqql8myKVrBxgzFLDrZXmyerkboN1m";
	
	static String tweetId = "";
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "https://api.twitter.com";
		RestAssured.basePath = "/1.1/statuses";
	}
	
	@Test
	// @Ignore
	public void test_01_postTweet() throws InterruptedException {
		Response resp = given()
			// .log().all()
			.auth()
			.oauth(consumerKey, consumerSecret, accessToken, accessSecret)
			.queryParam("status", "My API Testing Tweet")
		.when()
			.post("/update.json")
		.then()
			// .log().all()
			.statusCode(200)
			.extract().response();
		
		tweetId = resp.path("id_str");
		System.out.println("Tweet ID is: " + tweetId);
	}
	
	@Test
	public void test_02_readTweet() {
		Response resp = given()
			// .log().all()
			.auth()
			.oauth(consumerKey, consumerSecret, accessToken, accessSecret)
			.queryParam("id", tweetId)
		.when()
			.get("/show.json")
		.then()
			// .log().all()
			.extract()
			.response();
		
		String tweetText = resp.path("text");
		System.out.println("TWEET: " + tweetText);
	}
	
	@Test
	public void test_03_deleteTweet() {
		given()
			.log().all()
			.auth()
			.oauth(consumerKey, consumerSecret, accessToken, accessSecret)
			.pathParam("id", tweetId)
		.when()
			.post("/destroy/{id}.json")
		.then()
			// .log().all()
			.statusCode(200);
	}
}
