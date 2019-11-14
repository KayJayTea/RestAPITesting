package allure.examples;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import app.MySimpleCalculator;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.qameta.allure.junit4.DisplayName;

@Epic("All tools for calculation")
@Feature("Calculator for performing all mathematical operations")
@DisplayName("Calculator to perform mathematical calcualtions")
public class MyCalculatorTest {
	
	@DisplayName("Calculate the Sum of Two Numbers")
	@Story("Calculate the Sum of Two Numbers")
	@Description("This is a test method that will add 2 numbers."
			+ "This test focuses on the addition of 2 +ve numbers and checks if the result of "
			+ "the calculation is valid")
	@Test
	// @Ignore
	public void tc1() {
		MySimpleCalculator calculate = new MySimpleCalculator();		
		assertThat(calculate.add2num(3.0, 5.0), equalTo(8.0));
	}
	
	@TmsLink("TestLink")
	@DisplayName("Calculate the Difference of Two Numbers")
	@Story("Calculate the Difference of Two Numbers")
	@Description("This is a test method that will subtract 2 numbers."
			+ "This test focuses on the subtraction of 2 +ve numbers and checks if the result of "
			+ "the calculation is valid")
	@Test
	// @Ignore
	public void tc2() {
		MySimpleCalculator calculate = new MySimpleCalculator();
		assertThat(calculate.sub2num(10.0, 6.3), equalTo(3.7));
	}
	
	@Link("Software_testing")
	@DisplayName("Calculate the Product of Two Numbers")
	@Story("Calculate the Product of Two Numbers")
	@Description("This is a test method that will multiply 2 numbers."
			+ "This test focuses on the multiplication of 2 +ve numbers and checks if the result of "
			+ "the calculation is valid")
	@Test
	public void tc3() {
		MySimpleCalculator calculate = new MySimpleCalculator();
		assertThat(calculate.mul2num(6.5, 2.3), equalTo(14.95));
	}
	
	@Issue("Issue")
	@DisplayName("Calculate the Quotient of Two Numbers")
	@Story("Calculate the Quotient of Two Numbers")
	@Description("This is a test method that will divide 2 numbers."
			+ "This test focuses on the division of 2 +ve numbers and checks if the result of "
			+ "the calculation is valid")
	@Test
	// @Ignore
	public void tc4() {
		MySimpleCalculator calculate = new MySimpleCalculator();
		assertThat(calculate.div2num(20.0, 4.0), equalTo(6.0));
	}
	
	@DisplayName("Calculate the Sum of Two -ve Numbers")
	@Story("Calculate the Sum of Two Numbers")
	@Description("This is a test method that will add 2 -ve numbers."
			+ "This test focuses on the addition of 2 -ve numbers and checks if the result of "
			+ "the calculation is valid")
	@Test
	// @Ignore
	public void tc5() {
		CalculatorSteps calculate = new CalculatorSteps();
		calculate.add2numbers(-3.0, -5.0);
		calculate.add2numbers(-3.0, -5.0);
		assertThat(calculate.add2numbers(-3.0, -5.0), equalTo(-8.0));
	}
}
