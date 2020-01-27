package miscellaneoustests;

import static io.restassured.RestAssured.get;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.junit.Test;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

public class JSONSchemaValidation {

	@Test
	public void givenUrl_whenJsonResponseConformsToSchema_thenCorrect() {
		get("/events?id=390")
		.then()
			.assertThat()
			.body(matchesJsonSchemaInClasspath("event_0.json"));
	}
	
	@Test
	public void givenUrl_whenValidatesResponseWithInstanceSettings_thenCorrect() {
		JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
				.setValidationConfiguration(ValidationConfiguration.newBuilder()
						.setDefaultVersion(SchemaVersion.DRAFTV4)
						.freeze())
				.freeze();
		get("/events?id=390")
		.then()
			.assertThat()
			.body(matchesJsonSchemaInClasspath("event_0.json").using(jsonSchemaFactory));
	}
}
