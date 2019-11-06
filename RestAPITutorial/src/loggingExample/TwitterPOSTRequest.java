package loggingExample;

import static io.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

public class TwitterPOSTRequest {
	
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
	public void testMethod() {
		given()
			.log().headers()
			.auth()
			.oauth(consumerKey, consumerSecret, accessToken, accessSecret)
			.queryParam("status", "My API Testing Tweet")
		.when()
			.post("/update.json")
		.then()
			.statusCode(200);
	}

}
