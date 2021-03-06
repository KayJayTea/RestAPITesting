package com.studentapp.cucumber.steps;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.utils.TestUtils;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;

public class StudentSteps {
	
	static String email = null;
	
	@Steps
	StudentSerenitySteps steps;

	@When("^User sends a GET request to the list endpoint, they must get back a valid status code 200$")
	public void verify_stataus_code_200_for_list_endpoint() {
		SerenityRest.rest()
		.given()
		.when()
			.get("/list")
		.then()
			.statusCode(200);
	}
	
	@When("^User creates a new student by providing the firstName (.*) lastName (.*) email (.*) programme (.*) courses (.*)$")
	public void createStudent(String firstName, String lastName, String _email, String programme, String course) {
		List<String> courses = new ArrayList<>();
		courses.add(course);
		email = TestUtils.getRandomValue() + _email;
		
		steps.createStudent(firstName, lastName, email, programme , courses)
		.assertThat().statusCode(201);
	}
	
	@Then("^User verifies the student with (.*) is created$")
	public void verifyStudent(String emailId) {
		HashMap<String, Object> actualValue = steps.getStudentInfoByEmailId(email);
		assertThat(actualValue, hasValue(email));
	}
}
