package com.sharfah.util.collections;

import static org.hamcrest.CoreMatchers.is;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class StackWithMinTest {

	@Test
	public void test() {
		final StackWithMin<String> stack = new StackWithMin<>();
		stack.push("bob");
		MatcherAssert.assertThat(stack.min(), is("bob"));
		stack.push("alice");
		MatcherAssert.assertThat(stack.min(), is("alice"));
		stack.push("charlie");
		MatcherAssert.assertThat(stack.min(), is("alice"));
		stack.push("adam");
		MatcherAssert.assertThat(stack.min(), is("adam"));
		stack.pop();
		MatcherAssert.assertThat(stack.min(), is("alice"));
		MatcherAssert.assertThat(stack.peek(), is("charlie"));
	}
}
