package usefulTricks;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.lessThan;

import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;

public class TwitterCheckResponseTime {

	String consumerKey = "MlcBJLyNzgh1YjVbqY8vYU2Fp";
	String consumerSecret = "SwxRa657LAfbwTkOPDi5wcJoVfCRVPxrbiCfOqC6d5WhXhg27W";
	String accessToken = "303864833-ruWERl3CqF0VH2VQ2HfIAfh1SJVUBdjpIrlrsEla";
	String accessSecret = "sqdw55waRgspcDFwqql8myKVrBxgzFLDrZXmyerkboN1m";
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "https://api.twitter.com";
		RestAssured.basePath = "/1.1/statuses";
		RestAssured.rootPath = "user";
	}
	
	@Test
	public void timeTest_01() {
		long responseTime = 
		given()
			.auth()
			.oauth(consumerKey, consumerSecret, accessToken, accessSecret)
			.queryParam("user_id", "Stumps")
		.when()
			.get("/user_timeline.json")
			//.time()
			.timeIn(TimeUnit.SECONDS);
		
		System.out.println("Response Time is: " + responseTime);
	}
	
	@Test
	public void timeTest_02() {
		given()
			.auth()
			.oauth(consumerKey, consumerSecret, accessToken, accessSecret)
			.queryParam("user_id", "Stumps")
		.when()
			.get("/user_timeline.json")
		.then()
			.log().body()
			.statusCode(200)
			.time(lessThan(1000L), TimeUnit.MILLISECONDS)
			.body(
					"id_str", hasItem("303864833"),
					"name", hasItem("Stumps"),
					"screen_name", hasItem("kjtodd37"),
					"location", hasItem("Hampton, VA")
				);
	}
}
