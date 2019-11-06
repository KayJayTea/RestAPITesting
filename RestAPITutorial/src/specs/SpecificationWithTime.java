package specs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

import org.hamcrest.collection.HasItemInArray;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecificationWithTime {
	String consumerKey = "MlcBJLyNzgh1YjVbqY8vYU2Fp";
	String consumerSecret = "SwxRa657LAfbwTkOPDi5wcJoVfCRVPxrbiCfOqC6d5WhXhg27W";
	String accessToken = "303864833-ruWERl3CqF0VH2VQ2HfIAfh1SJVUBdjpIrlrsEla";
	String accessSecret = "sqdw55waRgspcDFwqql8myKVrBxgzFLDrZXmyerkboN1m";
	
	RequestSpecBuilder requestBuilder;
	static RequestSpecification requestSpec;
	ResponseSpecBuilder responseBuilder;
	static ResponseSpecification responseSpec;
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "https://api.twitter.com";
		RestAssured.basePath = "/1.1/statuses";
	}
	
	@Test
	public void readTweets() {
		given()
			.log().all()
			.spec(requestSpec)
		.when()
			.get("/user_timeline.json")
		.then()
			.log().all()
			.spec(responseSpec)
			.body("user.screen_name", hasItem("stumps"));
	}
}
