package Students.com.students.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import org.junit.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class student_tests {
	
	@BeforeClass
	public static void setupRestAssured() {
		RestAssured.baseURI = "http://localhost:8080";
		RestAssured.basePath = "/student/list";
		
		RequestSpecification requestSpecs = new RequestSpecBuilder().build();
		RestAssured.requestSpecification = requestSpecs;
	}
	
//	@Test
//	public void test_001_extractSngleValue_findSingleName() {
//		Response response = get();
//		List<String> jsonPath = response.jsonPath().getList("$");
////		System.out.println(jsonPath.size());
//		
//		for (int i = 0; i <jsonPath.size(); i++) {
//			System.out.println(jsonPath);
//		}
//	}
	public static Response getResponse(String endpoint) {
		RestAssured.defaultParser = Parser.JSON;
		
		return given()
				.when()
					.get(endpoint)
				.then()
					.contentType(ContentType.JSON)
					.body("firstName[0]", equalTo("Vernon")).and()
					//.body("$.firstName", contains("Murphy")).and()
					.extract().response();
	}
	
	public static void main(String[] args) {
		Response response = getResponse("http://localhost:8080/student/list");
		
		List<String> jsonResponse = response.jsonPath().getList("$");
		
		System.out.println(jsonResponse.size());
		
		String firstNames = response.jsonPath().getString("firstName");
		System.out.println(firstNames);
	}

}
