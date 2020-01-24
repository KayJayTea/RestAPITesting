package specs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpecificationDemo {

	static String consumerKey = "MlcBJLyNzgh1YjVbqY8vYU2Fp";
	static String consumerSecret = "SwxRa657LAfbwTkOPDi5wcJoVfCRVPxrbiCfOqC6d5WhXhg27W";
	static String accessToken = "303864833-ruWERl3CqF0VH2VQ2HfIAfh1SJVUBdjpIrlrsEla";
	static String accessSecret = "sqdw55waRgspcDFwqql8myKVrBxgzFLDrZXmyerkboN1m";

	static ResponseSpecBuilder responseBuilder;
	static ResponseSpecification responseSpec;

	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "https://api.twitter.com";
		RestAssured.basePath = "/1.1/statuses";
		RestAssured.rootPath = "user";
		
		responseBuilder = new ResponseSpecBuilder();
		responseBuilder.expectStatusCode(200);
		responseBuilder.expectBody("id_str", hasItem("303864833"));
		responseBuilder.expectBody("name", hasItem("Stumps"));
		responseBuilder.expectBody("screen_name", hasItem("kjtodd37"));
		responseSpec = responseBuilder.build();
	}

	@Test
	public void readTweets() {
		given()
			.auth()
			.oauth(consumerKey, consumerSecret, accessToken, accessSecret)
			.queryParam("user_id", "Stumps")
		.when()
			.get("/user_timeline.json")
		.then()
			.spec(responseSpec)
			.body("location", hasItem("Hampton, VA"));
	}
}
