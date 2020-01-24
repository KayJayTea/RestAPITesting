package com.twitter.statuses;

import static io.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.twitter.common.RestUtilities;
import com.twitter.constants.EndPoints;
import com.twitter.constants.Path;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TwitterWorkflowTest {
	
	static RequestSpecification reqSpec;
	static ResponseSpecification respSpec;
	static String tweetId = "";
	
	@BeforeClass
	public static void init() {
		reqSpec = RestUtilities.getRequestSpecification();
		reqSpec.basePath(Path.STATUSES);
		
		respSpec = RestUtilities.getResponseSpecification();
	}
	
	@Test
	public void test_01_postTweet() {
		Response resp = 
		given()
			.spec(RestUtilities.createQueryParam(reqSpec, "status", "My Tweet from Rest Assured API (testing)."))
		.when()
			.post(EndPoints.STATUSES_TWEET_POST)
		.then()
			.spec(respSpec)
			.extract()
			.response();
		
		JsonPath jsPath = RestUtilities.getJsonPath(resp);
		tweetId = jsPath.get("id_str");
		System.out.println("ID: " + tweetId);
	}
	
	@Test
	public void test_02_readTweet() {
		RestUtilities.setEndPoint(EndPoints.STATUSES_TWEET_READ_SINGLE);
		Response response = RestUtilities.getResponse(
				RestUtilities.createQueryParam(reqSpec, "id", tweetId), "get");
		
		String text = response.path("text");
		System.out.println("Tweet: " + text);
	}
	
	@Test
	public void test_03_deleteTweet() {
		given()
			.spec(RestUtilities.createPathParam(reqSpec, "id", tweetId))
		.when()
			.post(EndPoints.STATUSES_TWEET_DESTROY)
		.then()
			.spec(respSpec);
	}
}
