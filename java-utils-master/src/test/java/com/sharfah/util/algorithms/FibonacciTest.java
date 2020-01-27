package com.sharfah.util.algorithms;

import static com.sharfah.util.algorithms.Fibonacci.fibonacci;
import static com.sharfah.util.algorithms.Fibonacci.fibonacciMemoized;
import static com.sharfah.util.algorithms.Fibonacci.iterativeFibonacci;
import static org.hamcrest.CoreMatchers.is;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class FibonacciTest {

	@Test
	public void testRecursive() {
		MatcherAssert.assertThat(fibonacci(0), is(0));
		MatcherAssert.assertThat(fibonacci(1), is(1));
		MatcherAssert.assertThat(fibonacci(6), is(8));
	}

	@Test
	public void testRecursiveMemoized() {
		MatcherAssert.assertThat(fibonacciMemoized(0), is(0));
		MatcherAssert.assertThat(fibonacciMemoized(1), is(1));
		MatcherAssert.assertThat(fibonacciMemoized(6), is(8));
	}

	@Test
	public void testIterative() {
		MatcherAssert.assertThat(iterativeFibonacci(0), is(0));
		MatcherAssert.assertThat(iterativeFibonacci(1), is(1));
		MatcherAssert.assertThat(iterativeFibonacci(6), is(8));
	}
}
