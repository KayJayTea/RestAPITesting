package com.twitter.statuses;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.twitter.common.RestUtilitites;
import com.twitter.constants.Endpoints;
import com.twitter.constants.Path;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class UserTimelineTest {
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	
	@BeforeClass
	public void setup() {
		reqSpec = RestUtilitites.getRequestSpecification();
		reqSpec.queryParam("user_id", "kjtodd37");
		reqSpec.basePath(Path.STATUSES);
		
		resSpec = RestUtilitites.getResponseSpecification();
	}
	
	@Test
	public void readTweets1() {
		given()
			.spec(reqSpec)
		.when()
			.get(Endpoints.STATUSES_USER_TIMELINE)
		.then()
			// .log().all()
			.spec(resSpec)
			.body("user.screen_name", hasItem("kjtodd37"));
	}
	
	@Test
	public void readTweets2() {
		given()
			.spec(RestUtilitites.createQueryParam(reqSpec, "count", "2"))
		.when()
			.get(Endpoints.STATUSES_USER_TIMELINE)
		.then()
			// .log().all()
			.spec(resSpec)
			.body("user.screen_name", hasItem("kjtodd37"));
	}
	
	@Test
	public void readTweets3() {
		RestUtilitites.setEndPont(Endpoints.STATUSES_USER_TIMELINE);
		Response res = RestUtilitites.getResponse(
				RestUtilitites.createQueryParam(reqSpec, "count", "2"), "get");
		
		ArrayList<String> screenNameList = res.path("user.screen_name");
		Assert.assertTrue(screenNameList.contains("kjtodd37"));
	}
}
