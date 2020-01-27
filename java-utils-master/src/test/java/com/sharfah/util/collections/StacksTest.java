package com.sharfah.util.collections;

import static org.hamcrest.CoreMatchers.is;

import java.util.NoSuchElementException;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class StacksTest {

	@Test
	public void testMin() {
		final Stack<String> stack = new Stack<>();
		stack.push("bob");
		stack.push("alice");
		stack.push("charlie");
		MatcherAssert.assertThat(Stacks.min(stack), is("alice"));
		MatcherAssert.assertThat(stack.pop(), is("charlie"));
		MatcherAssert.assertThat(stack.pop(), is("alice"));
		MatcherAssert.assertThat(stack.pop(), is("bob"));
		MatcherAssert.assertThat(stack.isEmpty(), is(true));
	}

	@Test(expected = NoSuchElementException.class)
	public void testMinThrowsExceptionOnEmptyStack() {
		final Stack<String> stack = new Stack<>();
		Stacks.min(stack);
	}

	@Test
	public void testMove() {
		final Stack<String> stack = new Stack<>();
		stack.push("bob");
		stack.push("alice");
		stack.push("charlie");

		final Stack<String> stack2 = new Stack<>();
		stack2.push("foo");

		Stacks.move(stack, stack2);

		MatcherAssert.assertThat(stack.isEmpty(), is(true));
		MatcherAssert.assertThat(stack2.pop(), is("bob"));
		MatcherAssert.assertThat(stack2.pop(), is("alice"));
		MatcherAssert.assertThat(stack2.pop(), is("charlie"));
		MatcherAssert.assertThat(stack2.pop(), is("foo"));
	}

	@Test
	public void testSort() {
		final Stack<Integer> stack = new Stack<>();
		stack.push(10);
		stack.push(3);
		stack.push(1);
		stack.push(5);
		stack.push(4);
		stack.push(2);
		Stacks.sort(stack);
		MatcherAssert.assertThat(stack.pop(), is(1));
		MatcherAssert.assertThat(stack.pop(), is(2));
		MatcherAssert.assertThat(stack.pop(), is(3));
		MatcherAssert.assertThat(stack.pop(), is(4));
		MatcherAssert.assertThat(stack.pop(), is(5));
		MatcherAssert.assertThat(stack.pop(), is(10));
	}
}
