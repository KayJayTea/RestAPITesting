package specs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecificationWithTime {
	static String consumerKey = "MlcBJLyNzgh1YjVbqY8vYU2Fp";
	static String consumerSecret = "SwxRa657LAfbwTkOPDi5wcJoVfCRVPxrbiCfOqC6d5WhXhg27W";
	static String accessToken = "303864833-ruWERl3CqF0VH2VQ2HfIAfh1SJVUBdjpIrlrsEla";
	static String accessSecret = "sqdw55waRgspcDFwqql8myKVrBxgzFLDrZXmyerkboN1m";
	
	static RequestSpecBuilder requestBuilder;
	static RequestSpecification requestSpec;
	static ResponseSpecBuilder responseBuilder;
	static ResponseSpecification responseSpec;
	
	@BeforeClass
	public static void setup() {
		AuthenticationScheme authScheme = RestAssured.oauth(consumerKey, consumerSecret, accessToken, accessSecret);
		RestAssured.rootPath = "user";
		
		requestBuilder = new RequestSpecBuilder();
		requestBuilder.setBaseUri("https://api.twitter.com");
		requestBuilder.setBasePath("/1.1/statuses");
		requestBuilder.addQueryParam("user_id", "Stumps");
		requestBuilder.setAuth(authScheme);
		requestSpec = requestBuilder.build();
		
		responseBuilder = new ResponseSpecBuilder();
		responseBuilder.expectStatusCode(200);
		responseBuilder.expectResponseTime(lessThan(2000L), TimeUnit.MILLISECONDS);
		responseBuilder.expectBody("id_str", hasItem("303864833"));
		responseBuilder.expectBody("name", hasItem("Stumps"));
		responseBuilder.expectBody("screen_name", hasItem("kjtodd37"));
		responseSpec = responseBuilder.build();
	}
	
	@Test
	public void readTweets() {
		given()
			.spec(requestSpec)
		.when()
			.get("/user_timeline.json")
		.then()
			.spec(responseSpec)
			.body("location", hasItem("Hampton, VA"));
	}
}
