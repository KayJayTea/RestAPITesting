package com.fileupload.example;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.junit.Test;

public class FileUploadExample {
	
	/** 
	 * Upload a file (.gif) to Zamzar.com and convert it to a png file
	 * 
	 * Zamzar API Key = 784c5bb7d3dbc63974c9d1018fa591159999ed4d
	 */
	
	@Test
	public void uploadFileExample() {
		String apiKey = "784c5bb7d3dbc63974c9d1018fa591159999ed4d";
		File inputFile = new File(System.getProperty("user.dir")+File.separator+"cube_chrome.gif");
		String endPoint = "https://sandbox.zamzar.com/v1/jobs";
		
		given()
		.auth().basic("key", apiKey)
		.multiPart("source_file", inputFile)
		.multiPart("target_format", "png")
		.when()
		.post(endPoint)
		.then()
		.log()
		.all();
	}

}
