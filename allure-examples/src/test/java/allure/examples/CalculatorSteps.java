package allure.examples;

import app.MySimpleCalculator;
import io.qameta.allure.Step;

public class CalculatorSteps {
	
	@Step("Adding two numbers: {0} & {1}")
	public double add2numbers(double n1, double n2) {
		MySimpleCalculator calculate = new MySimpleCalculator();
		return calculate.add2num(n1, n2);
	}

}
