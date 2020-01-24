package com.student.tests;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

@RunWith(DataProviderRunner.class)
public class DataDrivenTest {
	
	@DataProvider
	public static Object[][] dataAdd() {
		return new Object[][] {
			{0, 1},
			{1, 2},
			{3, 4}
		};
	}
	
	@UseDataProvider("dataAdd")
	@Test()
	public void addTwoNumbers(int num1, int num2) {
		System.out.println(num1 + num2);
	}
	
	@Test()
	public void printStuff() {
		System.out.println("Hello");
	}
}
