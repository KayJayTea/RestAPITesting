package twitterAPIExample;

import static io.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class TwitterExtractResponse {
	
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
	public void postTweet() {
		Response resp = given()
			.auth()
			.oauth(consumerKey, consumerSecret, accessToken, accessSecret)
			.queryParam("status", "My API Testing Tweet")
		.when()
			.post("/update.json")
		.then()
			.statusCode(200)
			.extract().response();
		
		String id = resp.path("id_str");
		System.out.println(id);
		
		String respString = resp.asString();
		System.out.println(respString);
		
		JsonPath jsonPath = new JsonPath(respString);
		String name = jsonPath.get("user.name");
		System.out.println(name);
	}

}
