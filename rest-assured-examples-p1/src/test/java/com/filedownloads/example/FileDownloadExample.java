package com.filedownloads.example;

import java.io.File;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;



public class FileDownloadExample {

	// download a file and compare it with expected file
	// check the size of the file
	@Test
	public void verifyDownloadedFiles() {
		//Read the input file
		File inputFile = new File(System.getProperty("user.dir")+File.separator+"geckodriver-v0.26.0-win32.zip");
		
		// Length of inputFile
		int expectedSize = (int)inputFile.length();
		
		System.out.println("The size of the expected file: " + expectedSize + " bytes");
		
		// https://github.com/mozilla/geckodriver/releases/download/v0.26.0/geckodriver-v0.26.0-win32.zip
		
		// Read the downloaded file
		byte[] actualSize = given()
				.when()
				.get("https://github.com/mozilla/geckodriver/releases/download/v0.26.0/geckodriver-v0.26.0-win32.zip")
				.then()
				.extract()
				.asByteArray();
		System.out.println("The size of the expected file: " + actualSize.length + " bytes");
		
		assertThat(expectedSize, equalTo(actualSize.length));
	}
}
