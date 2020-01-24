package assertExamples;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

public class TwitterHardAssertExample {
	
	String consumerKey = "MlcBJLyNzgh1YjVbqY8vYU2Fp";
	String consumerSecret = "SwxRa657LAfbwTkOPDi5wcJoVfCRVPxrbiCfOqC6d5WhXhg27W";
	String accessToken = "303864833-ruWERl3CqF0VH2VQ2HfIAfh1SJVUBdjpIrlrsEla";
	String accessSecret = "sqdw55waRgspcDFwqql8myKVrBxgzFLDrZXmyerkboN1m";
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "https://api.twitter.com";
		RestAssured.basePath = "/1.1/statuses";
	}
	
	@Test
	public void readTweets() {
		given()
			.auth()
			.oauth(consumerKey, consumerSecret, accessToken, accessSecret)
			.queryParam("user_id", "Stumps")
			.queryParam("count", "2")
		.when()
			.get("/user_timeline.json")
		.then()
			// .log().body()
			.statusCode(200)
			.body("user.name", hasItem("Stumps"))
			.body("entities.user_mentions[0].screen_name", hasItem("BBCWorld1"))
			.body("entities.user_mentions[0].size", lessThan(2))
			.body("entities.user_mentions[1].size", equalTo(3));
	}
}
