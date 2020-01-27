package com.sharfah.util.collections;

import static org.hamcrest.CoreMatchers.is;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class SetOfStacksTest {

	@Test
	public void testPush() {
		final SetOfStacks<String> stack = new SetOfStacks<>(3);
		MatcherAssert.assertThat(stack.isEmpty(), is(true));
		stack.push("foo");
		MatcherAssert.assertThat(stack.peek(), is("foo"));
		stack.push("bar");
		MatcherAssert.assertThat(stack.peek(), is("bar"));
		stack.push("baz");
		MatcherAssert.assertThat(stack.peek(), is("baz"));
		stack.push("one");
		MatcherAssert.assertThat(stack.peek(), is("one"));
		stack.push("two");
		MatcherAssert.assertThat(stack.peek(), is("two"));
	}

	@Test
	public void testPop() {
		final SetOfStacks<String> stack = new SetOfStacks<>(3);
		stack.push("foo");
		MatcherAssert.assertThat(stack.pop(), is("foo"));
		stack.push("foo");
		stack.push("bar");
		stack.push("baz");
		stack.push("one");
		stack.push("two");
		MatcherAssert.assertThat(stack.pop(), is("two"));
		MatcherAssert.assertThat(stack.pop(), is("one"));
		MatcherAssert.assertThat(stack.pop(), is("baz"));
		MatcherAssert.assertThat(stack.pop(), is("bar"));
		MatcherAssert.assertThat(stack.pop(), is("foo"));
		MatcherAssert.assertThat(stack.isEmpty(), is(true));
	}

	@Test
	public void testIsEmpty() {
		final SetOfStacks<String> stack = new SetOfStacks<>(3);
		MatcherAssert.assertThat(stack.isEmpty(), is(true));
		stack.push("foo");
		MatcherAssert.assertThat(stack.pop(), is("foo"));
		MatcherAssert.assertThat(stack.isEmpty(), is(true));
	}

	@Test
	public void testPopAt() {
		final SetOfStacks<String> stack = new SetOfStacks<>(3);
		stack.push("foo");
		stack.push("bar");
		stack.push("baz");
		stack.push("one");
		stack.push("two");

		MatcherAssert.assertThat(stack.popAt(0), is("baz"));

		MatcherAssert.assertThat(stack.pop(), is("two"));
		MatcherAssert.assertThat(stack.pop(), is("one"));
		MatcherAssert.assertThat(stack.pop(), is("bar"));
		MatcherAssert.assertThat(stack.pop(), is("foo"));
	}

	@Test
	public void testPeekAt() {
		final SetOfStacks<String> stack = new SetOfStacks<>(3);
		stack.push("foo");
		stack.push("bar");
		stack.push("baz");
		stack.push("one");
		stack.push("two");
		MatcherAssert.assertThat(stack.peekAt(1), is("two"));
		MatcherAssert.assertThat(stack.peekAt(0), is("baz"));
		MatcherAssert.assertThat(stack.popAt(0), is("baz"));
		MatcherAssert.assertThat(stack.peekAt(1), is("two"));
		MatcherAssert.assertThat(stack.peekAt(0), is("one"));
	}

}
