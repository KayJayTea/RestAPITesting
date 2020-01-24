package loggingexample;

import static io.restassured.RestAssured.given;

import org.junit.Before;
import org.junit.Test;

import io.restassured.RestAssured;

public class TwitterPostRequest {
	
	String consumerKey = "MlcBJLyNzgh1YjVbqY8vYU2Fp";
	String consumerSecret = "SwxRa657LAfbwTkOPDi5wcJoVfCRVPxrbiCfOqC6d5WhXhg27W";
	String accessToken = "303864833-ruWERl3CqF0VH2VQ2HfIAfh1SJVUBdjpIrlrsEla";
	String tokenSecret = "sqdw55waRgspcDFwqql8myKVrBxgzFLDrZXmyerkboN1m";
	
	@Before
	public void init() {
		RestAssured.baseURI = "https://api.twitter.com";
		RestAssured.basePath = "/1.1/statuses";
	}
	
	@Test
	public void postTweet() {
		given()
			.auth()
			.oauth(consumerKey, consumerSecret, accessToken, tokenSecret)
			.queryParam("status", "My Tweet from Rest Assured API (testing).")
		.when()
			.post("/update.json")
		.then()
			.statusCode(200);
	}
}
