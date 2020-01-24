package com.student.requests;

import java.util.List;

import com.student.pojo.StudentClass;
import com.student.tests.TestBase;

import io.qameta.allure.Step;
import io.restassured.response.Response;

public class RequestFactory extends TestBase {
	
	RestClient restClient = new RestClient();
	
	@Step("Getting all the student info from database")
	public Response getAllStudents() {
		Response response = restClient.doGetRequest("/list");
		
		return response;
	}
	
	@Step("Creating new student: {0}, {1}, {2}, {3}, {4}")
	public Response createNewStudent(String uri, String firstName, String lastName, String email, String program, List<String> courses) {
		StudentClass body = new StudentClass();
		
		body.setFirstName(firstName);
		body.setLastName(lastName);
		body.setEmail(email);
		body.setProgramme(program);
		body.setCourses(courses);
		
		return restClient.doPostRequest(uri, body);
	}
	
	@Step("Updating student information with studentId: {0}, {1}, {2}, {3}, {4}")
	public Response updateStudent(String uri, String firstName, String lastName, String email, String program, List<String> courses, int studentId) {
		StudentClass student = new StudentClass();
		
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setProgramme(program);
		student.setCourses(courses);
		
		return restClient.doPutRequest("/" + studentId, student);
	}
	
	@Step("Deleting student info with Id: {0}")
	public Response deleteStudentById(int studentId) {
		return restClient.doDeleteRequest("/" + studentId);
	}
	
	@Step("Getting student info with Id: {0}")
	public Response getStudentById(int studentId) {
		return restClient.doGetRequest("/" + studentId);
	}
	
	
}
