package com.sharfah.util.algorithms;

import static com.sharfah.util.algorithms.Factorial.bigFactorial;
import static com.sharfah.util.algorithms.Factorial.factorial;
import static com.sharfah.util.algorithms.Factorial.iterativeFactorial;
import static com.sharfah.util.algorithms.Factorial.streamFactorial;
import static com.sharfah.util.algorithms.Factorial.tailRecursiveFactorial;
import static org.hamcrest.CoreMatchers.is;

import java.math.BigInteger;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class FactorialTest {

	@Test
	public void testRecursive() {
		MatcherAssert.assertThat(factorial(5), is(120));
		MatcherAssert.assertThat(factorial(0), is(1));
	}

	@Test
	public void testTailRecursive() {
		MatcherAssert.assertThat(tailRecursiveFactorial(5), is(120));
		MatcherAssert.assertThat(tailRecursiveFactorial(0), is(1));
	}

	@Test
	public void testIterative() {
		MatcherAssert.assertThat(iterativeFactorial(5), is(120));
		MatcherAssert.assertThat(iterativeFactorial(0), is(1));
	}

	@Test
	public void testBigInteger() {
		MatcherAssert.assertThat(bigFactorial(5), is(BigInteger.valueOf(120)));
		MatcherAssert.assertThat(bigFactorial(0), is(BigInteger.ONE));
	}

	@Test
	public void testStream() {
		MatcherAssert.assertThat(streamFactorial(5), is(120));
		MatcherAssert.assertThat(streamFactorial(0), is(1));
	}

}
