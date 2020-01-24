package com.student.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import com.github.javafaker.Faker;
import com.student.requests.RequestFactory;
import com.student.specs.SpecificationFactory;
import com.student.tags.Regression;
import com.student.tags.Smoke;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.qameta.allure.junit4.Tag;

@Story("This is a CRUD testing story")
public class CrudTest extends TestBase {
	
	RequestFactory requests = new RequestFactory();
	
	@Category(Smoke.class)
	@Story("This is a CRUD testing story")
	@DisplayName("Test to get ALL students from database")
	@Feature("Test to get ALL students from database")
	@Tag("Smoke")
	@Test
	@Ignore
	public void getAllStuduents() {
		requests.getAllStudents()
		.then()
		.spec(SpecificationFactory.getGenericResponseSpec())
		.statusCode(200);
	}
	
	@Category({Regression.class, Smoke.class})
	@Story("This is a CRUD testing story")
	@DisplayName("Test to create and verify a new student")
	@Feature("Test to create and verify a new student")
	@Tag("Regression, Smoke")
	@Test
	@Ignore
	public void createNewStudent() {		
		Faker fakeData = new Faker();
		String firstName = fakeData.name().firstName();
		String lastName = fakeData.name().lastName();
		String email = fakeData.internet().emailAddress();
		String program = "Computer Science";
		List<String> courses = new ArrayList<String>();
		courses.add("C++");
		courses.add("Java");
		
		requests.createNewStudent("", firstName, lastName, email, program, courses)
		.then()
		.spec(SpecificationFactory.getGenericResponseSpec())
		.statusCode(201);
	}
	
	@Story("This is a CRUD testing story")
	@DisplayName("Test to read student using studentId")
	@Feature("Test to read student using studentId")
	@Test
	@Ignore
	public void readNewStudent() {
		requests.getStudentById(100);
	}
	
	@Story("This is a CRUD testing story")
	@DisplayName("Test to delete student using studentId")
	@Feature("Test to delete student using studentId")
	@Test
	// @Ignore
	public void deleteStudentUsingId() {
		requests.deleteStudentById(101);
	}
}
