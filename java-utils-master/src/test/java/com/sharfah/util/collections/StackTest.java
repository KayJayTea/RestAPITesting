package com.sharfah.util.collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class StackTest {

	@Test
	public void testPush() {
		final Stack<String> stack = new Stack<>();
		assertTrue(stack.isEmpty());
		stack.push("foo");
		MatcherAssert.assertThat(stack.peek(), is("foo"));
		stack.push("bar");
		MatcherAssert.assertThat(stack.peek(), is("bar"));
		assertFalse(stack.isEmpty());
	}

	@Test
	public void testPop() {
		final Stack<String> stack = new Stack<>();
		stack.push("foo");
		stack.push("bar");
		MatcherAssert.assertThat(stack.peek(), is("bar"));
		MatcherAssert.assertThat(stack.pop(), is("bar"));
		MatcherAssert.assertThat(stack.peek(), is("foo"));
		MatcherAssert.assertThat(stack.pop(), is("foo"));
	}

	@Test(expected = NoSuchElementException.class)
	public void testPopOnEmptyStack() {
		new Stack<>().pop();
	}

	@Test
	public void testPeek() {
		final Stack<String> stack = new Stack<>();
		stack.push("foo");
		MatcherAssert.assertThat(stack.peek(), is("foo"));
		stack.push("bar");
		MatcherAssert.assertThat(stack.peek(), is("bar"));
	}

	@Test(expected = NoSuchElementException.class)
	public void testPeekOnEmptyStack() {
		new Stack<>().peek();
	}

	@Test
	public void testIsEmpty() {
		final Stack<String> stack = new Stack<>();
		assertTrue(stack.isEmpty());
		stack.push("foo");
		assertFalse(stack.isEmpty());
		stack.pop();
		assertTrue(stack.isEmpty());
	}

	@Test
	public void testToString() {
		final Stack<String> stack = new Stack<>();
		MatcherAssert.assertThat(stack.toString(), is("[]"));
		stack.push("foo");
		MatcherAssert.assertThat(stack.toString(), is("[foo]"));
		stack.push("bar");
		MatcherAssert.assertThat(stack.toString(), is("[bar, foo]"));
		stack.pop();
		MatcherAssert.assertThat(stack.toString(), is("[foo]"));
	}
}
