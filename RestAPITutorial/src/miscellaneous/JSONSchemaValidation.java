package miscellaneous;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.junit.Test;

import io.restassured.RestAssured;

public class JSONSchemaValidation {

	@Test
	public void testJsonSchema() {
		RestAssured.given()
		.log().all()
		.when()
		.get("https://jsonplaceholder.typicode.com/comments?id=500")
		.then()
		.log().all()
		.assertThat()
		.body(matchesJsonSchemaInClasspath("JsonSchemaFile.json"));
	}
}
