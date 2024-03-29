package gpath.com.tests;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class gpath_tests {
	
	@BeforeClass
	public static void setupRestAssured() {
		RestAssured.baseURI = "http://api.football-data.org";
		RestAssured.basePath = "/v2/";
		RequestSpecification requestSpecs = new RequestSpecBuilder()
				.addHeader("X-Auth-Token", "11601c3eb7e24713b163491f2782eb99")
				.addHeader("X-Response-Control", "minified")
				.build();
		
		RestAssured.requestSpecification = requestSpecs;
		
	}
	
	@Test
	public void test_001_extractSingleValue_findSingleTeamName() {
		Response response = get("teams/66");
		String teamName = response.path("name");
		System.out.println("01. Find a Single Team: " + teamName);
	}
	
	@Test
	public void test_002_extractSingleValue_findSingleTeamName_specifyJsonPath() {
		Response response = get("teams/66");
		JsonPath jsonPath = new JsonPath(response.asString());
		String teamName = jsonPath.get("name");
		System.out.println("02. Specify JsonPAth: " + teamName);
	}
	
	@Test
	public void test_003_extractSingleValue_findSingleTeamName_responseAsAString() {
		String responseAsAString = get("teams/66").asString();
		String teamName = JsonPath.from(responseAsAString).get("name");
		System.out.println("03. Response as a String: " + teamName);
	}
	
	@Test
	public void test_004_extractSingleValue_findSingleTeamName_getEverythingInOneGo() {
		String teamName = get("teams/66").path("name");
		System.out.println("04. Get everything in one go: " + teamName);
	}
	
	@Test
	public void test_005_extractSingleValue_findSingleTeamName_useAssertion() {
		given()
			.when()
			.get("teams/66")
			.then().assertThat()
			.body("name", equalTo("Manchester United FC"));
	}
	
	@Test
	public void test_006_extractFirstValueWhenSeveralAreReturned_findFirstTeamName() {
		Response response = get("competitions");
		String firstTeamName = response.path("competitions.name[0]");
		System.out.println("06. First Team Name: " + firstTeamName);
	}
	
	@Test
	public void test_007_extractFirstValueWhenSeveralAreReturned_findLastTeamName() {
		Response response = get("competitions");
		String lastTeamName = response.path("competitions.name[-1]");
		System.out.println("07. Last Team Name: " + lastTeamName);
	}
	
	// Extract All Values into a List
	@Test
	public void test_008_extractListOfValues_findAllTeamNames() {
		Response response = get("teams");
		ArrayList<String> allTeamNames = response.path("teams.name");
		System.out.println("08. " + allTeamNames);
	}
	
	// Extract Multiple Maps of Objects
	@Test
	public void test_009_extractListOfMapsOfElements_findAllTeamData() {
		Response response = get("teams");
		ArrayList<Map<String,?>> allTeamData = response.path("teams");
		System.out.println("09. " + allTeamData);
	}
	
	// Extract Map of JSON Object with Find
	@Test
	public void test_010_extractMapOfElementsWithFind_findAllTeamDataForSingleTeam() {
		Response response = get("teams");
		Map<String,?> allTeamDataForSingleTeam = response.path("teams.find {it.name == 'Leicester City FC'}");
		System.out.println("10. " + allTeamDataForSingleTeam);
	}
	
	// Extract List of Values with FindAll
	@Test
	public void test_011_extractListOfValuesWithFindAll_findAllPlayersWithJerseyNumberGreaterThan10() {
		Response response = get("teams/66");
		List<String> playerNames = response.path("squad.findAll {it.shirtNumber > 10}.name");
		System.out.println("11. " + playerNames);
	}
	
	// Extract Single Value with Max and Min
	@Test
	@Ignore
	public void test_012_extractSingleValuesWithHighestValueOfOtherElement_findHighestPlayerNumber() {
		Response response = get("teams/66");
		String highestNumberPlayer = response.path("squad.max {it.shirtNumber}.name");
		System.out.println("12. " + highestNumberPlayer);
	}
	
	@Test
	@Ignore
	public void test_013_extractSingleValueWithLowestValueOfOtherElement_findLowestPlayerNumber() {
	    Response response = get("teams/66");
	    String lowestNumberPlayer = response.path("squad.min { it.shirtNumber }.name");
	    System.out.println("13. " + lowestNumberPlayer);
	}
	
	// Collect a list of values and sum() them
	@Test
	public void test_014_extractMultipleValuesWithCollectAndSumThem_addUpAllJerseyNumbers() {
		Response response = get("teams/66");
		int sumOfJerseys = response.path("squad.collect {it.shirtNumber}.sum()");
		System.out.println("14. " + sumOfJerseys);
	}

}
