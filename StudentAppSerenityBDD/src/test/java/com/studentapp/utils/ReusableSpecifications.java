package com.studentapp.utils;

import static org.hamcrest.Matchers.*;

import java.util.concurrent.TimeUnit;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ReusableSpecifications {
	
	public static RequestSpecBuilder reqSpecBuilder;
	public static RequestSpecification reqSpec;
	public static ResponseSpecBuilder resSpecBuilder;
	public static ResponseSpecification resSpec;
	
	public static RequestSpecification getGenericRequestSpec() {
		reqSpecBuilder = new RequestSpecBuilder();
		reqSpecBuilder.setContentType(ContentType.JSON);
		reqSpec = reqSpecBuilder.build();
		return reqSpec;
	}
	
	public static ResponseSpecification getGenericResponse() {
		resSpecBuilder = new ResponseSpecBuilder();
		resSpecBuilder.expectHeader("Content-Type", "application/json;charset=UTF-8");
		resSpecBuilder.expectHeader("Transfer-Encoding", "chunked");
		resSpecBuilder.expectResponseTime(lessThan(5L), TimeUnit.SECONDS);
		resSpec = resSpecBuilder.build();
		return resSpec;
	}
}
