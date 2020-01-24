package specs;

import static io.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationDemo {

	static String consumerKey = "MlcBJLyNzgh1YjVbqY8vYU2Fp";
	static String consumerSecret = "SwxRa657LAfbwTkOPDi5wcJoVfCRVPxrbiCfOqC6d5WhXhg27W";
	static String accessToken = "303864833-ruWERl3CqF0VH2VQ2HfIAfh1SJVUBdjpIrlrsEla";
	static String accessSecret = "sqdw55waRgspcDFwqql8myKVrBxgzFLDrZXmyerkboN1m";
	
	static RequestSpecBuilder requestBuilder;
	static RequestSpecification requestSpec;
	
	@BeforeClass
	public static void setup() {
		AuthenticationScheme authScheme = RestAssured.oauth(consumerKey, consumerSecret, accessToken, accessSecret);
		
		requestBuilder = new RequestSpecBuilder();
		requestBuilder.setBaseUri("https://api.twitter.com");
		requestBuilder.setBasePath("/1.1/statuses");
		requestBuilder.addQueryParam("user_id", "Stumps");
		requestBuilder.setAuth(authScheme);
		requestSpec = requestBuilder.build();
	}
	
	@Test
	public void readTweets() {
		given()
			.spec(requestSpec)
		.when()
			.get("/user_timeline.json")
		.then()
			.statusCode(200);
	}
}
