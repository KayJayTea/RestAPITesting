package com.twitter.statuses;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.twitter.common.RestUtilities;
import com.twitter.constants.EndPoints;
import com.twitter.constants.Path;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class UserTimelineTest {
	
	static RequestSpecification reqSpec;
	static ResponseSpecification respSpec;
	JsonPath tweetId;
	
	@BeforeClass
	public static void init() {
		reqSpec = RestUtilities.getRequestSpecification();
		reqSpec.queryParam("user_id", "stumps");
		reqSpec.basePath(Path.STATUSES);
		
		respSpec = RestUtilities.getResponseSpecification();
	}
	
	@Test
	// PREFERRABLE
	public void getTweets1() {
		given()
			.spec(RestUtilities.createQueryParam(reqSpec, "count", "2"))
		.when()
			.get(EndPoints.STATUSES_USER_TIMELINE)
		.then()
			// .log().all()
			.spec(respSpec)
			.body("user.screen_name", hasItem("kjtodd37"));
	}
	
	@Test
	// Another way to test above test
	public void getTweets2() {
		RestUtilities.setEndPoint(EndPoints.STATUSES_USER_TIMELINE);
		Response resp = RestUtilities.getResponse(
				RestUtilities.createQueryParam(reqSpec, "count", "5"), "get");
		
		ArrayList<String> screenNameList = resp.path("user.screen_name");
		Assert.assertTrue(screenNameList.contains("kjtodd37"));
	}
}
