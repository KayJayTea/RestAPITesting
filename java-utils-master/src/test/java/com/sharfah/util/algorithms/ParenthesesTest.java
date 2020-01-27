package com.sharfah.util.algorithms;

import static org.hamcrest.CoreMatchers.hasItems;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class ParenthesesTest {

	@Test
	public void test() {
		MatcherAssert.assertThat(Parentheses.generate(1), hasItems("()"));
		MatcherAssert.assertThat(Parentheses.generate(2), hasItems("()()", "(())"));
		MatcherAssert.assertThat(Parentheses.generate(3), hasItems("()()()", "((()))", "()(())", "(())()", "(()())"));
	}
}
