package com.studentapp.cucumber.serenity;

import java.util.HashMap;
import java.util.List;

import com.studentapp.model.StudentClass;
import com.studentapp.utils.ReusableSpecifications;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

public class StudentSerenitySteps {

	@Step("CREATING Student with firstName: {0}, lastName: {1}, email: {2}, programme: {3}, and courses: {4}")
	public ValidatableResponse createStudent(String fName, String lName, String email, String programme,
			List<String> courses) {

		StudentClass student = new StudentClass();
		student.setFirstName(fName);
		student.setLastName(lName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);

		return SerenityRest.rest().given().spec(ReusableSpecifications.getGenericRequestSpec()).when().body(student)
				.post().then().statusCode(201);
	}

	@Step("READING Student Info with First Name {0}")
	public HashMap<String, Object> getStudentInfoByFirstName(String fName) {
		return SerenityRest.given().when().get("/list").then().statusCode(200).extract()
				.path("findAll{it.firstName=='" + fName + "'}.get(0)");
	}

	@Step("UPDATING Student with Student ID: {5}")
	public ValidatableResponse updateStudentById(String fName, String lName, String email, String programme,
			List<String> courses, int studentId) {
		StudentClass student = new StudentClass();
		student.setFirstName(fName);
		student.setLastName(fName);
		student.setEmail(email);
		student.setProgramme(programme);
		student.setCourses(courses);

		return SerenityRest.rest().given().spec(ReusableSpecifications.getGenericRequestSpec()).when().body(student)
				.put("/" + studentId).then().statusCode(200);
	}

	@Step("DELETE Student with ID, {0}, for the 'Student CRUD Test' Suite")
	public void deleteStudentById(int studentId) {
		SerenityRest.rest().given().when().delete("/" + studentId);
	}

	@Step("Verify Student with ID {0} is not found in JSON Response")
	public ValidatableResponse verify404Response(int studentId) {
		return SerenityRest.rest().given().when().get("/" + studentId).then().statusCode(404);
	}
	
	@Step
	public HashMap<String, Object> getStudentInfoByEmailId(String email) {
		return SerenityRest.rest()
				.given()
				.when()
				.get("/list")
				.then()
				.extract()
				.path("findAll{it.email=='"+email+"'}.get(0)");
	}
}
