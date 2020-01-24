package com.studentapp.junit.studentsinfo;

import static org.hamcrest.Matchers.hasValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import com.github.javafaker.Faker;
import com.studentapp.cucumber.serenity.StudentSerenitySteps;
import com.studentapp.testbase.TestBase;
import com.studentapp.utils.ReusableSpecifications;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;

@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StudentsCRUDTest extends TestBase {
	static Faker fakeData = new Faker();
	static String firstName = fakeData.name().firstName();
	static String lastName = fakeData.name().lastName();
	static String email = fakeData.internet().emailAddress();
	static String programme = "Computer Science";
	static int studentId;

	@Steps
	StudentSerenitySteps steps;

	@Title("This Test CREATES a New Student")
	@Test
	public void test001() {
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("Python");

		steps.createStudent(firstName, lastName, email, programme, courses)
		.spec(ReusableSpecifications.getGenericResponse());
	}

	@Title("This Test READS Created Student Added to the Application")
	@Test
	public void test002() {
		HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstName);
		assertThat(value, hasValue(firstName));
		studentId = (int) value.get("id");
	}

	@Title("This Test UPDATES the Last Student Created in the Application")
	@Test
	public void test003() throws InterruptedException {
		ArrayList<String> courses = new ArrayList<String>();
		courses.add("JAVA");
		courses.add("Python");

		firstName = firstName + "_UPDATED";
		steps.updateStudentById(firstName, lastName, email, programme, courses, studentId);

		HashMap<String, Object> value = steps.getStudentInfoByFirstName(firstName);

		assertThat(value, hasValue(firstName));
	}

	@Title("DELETE the Created Student and Verify Student was removed from JSON response")
	@Test
	public void test004() {
		steps.deleteStudentById(studentId);
		steps.verify404Response(studentId);
	}
}
