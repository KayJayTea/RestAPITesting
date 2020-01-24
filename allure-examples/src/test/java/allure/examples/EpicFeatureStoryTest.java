package allure.examples;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;

@Epic("An Epic")
@Feature("Pass Feature")
public class EpicFeatureStoryTest {

	@DisplayName("I am a test that will always pass")
	@Story("I am a test that will always pass")
	@Test
	public void tc1() {
		assertTrue(true);
	}
}
