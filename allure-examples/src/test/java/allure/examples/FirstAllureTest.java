package allure.examples;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;

@Epic("All tools for calculation")
@Feature("A feature that passes")
public class FirstAllureTest {
	
	@Story("My First Allure Test")
	@DisplayName("My First Allure Test")
	@Test
	public void test1() {
		assertTrue(true);
	}
}
