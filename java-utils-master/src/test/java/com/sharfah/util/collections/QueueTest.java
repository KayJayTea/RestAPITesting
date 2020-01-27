package com.sharfah.util.collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class QueueTest {

	@Test
	public void testAdd() {
		final Queue<String> queue = new Queue<>();
		assertTrue(queue.isEmpty());
		queue.add("foo");
		MatcherAssert.assertThat(queue.peek(), is("foo"));
		queue.add("bar");
		MatcherAssert.assertThat(queue.peek(), is("foo"));
		assertFalse(queue.isEmpty());
	}

	@Test
	public void testRemove() {
		final Queue<String> queue = new Queue<>();
		queue.add("foo");
		queue.add("bar");
		MatcherAssert.assertThat(queue.remove(), is("foo"));
		MatcherAssert.assertThat(queue.remove(), is("bar"));
		assertTrue(queue.isEmpty());
	}

	@Test(expected = NoSuchElementException.class)
	public void testRemoveOnEmptyQueue() {
		new Queue<>().remove();
	}

	@Test
	public void testPeek() {
		final Queue<String> queue = new Queue<>();
		queue.add("foo");
		MatcherAssert.assertThat(queue.peek(), is("foo"));
		queue.add("bar");
		MatcherAssert.assertThat(queue.peek(), is("foo"));
	}

	@Test(expected = NoSuchElementException.class)
	public void testPeekOnEmptyQueue() {
		new Queue<>().peek();
	}

	@Test
	public void testIsEmpty() {
		final Queue<String> queue = new Queue<>();
		assertTrue(queue.isEmpty());
		queue.add("foo");
		assertFalse(queue.isEmpty());
		MatcherAssert.assertThat(queue.remove(), is("foo"));
		assertTrue(queue.isEmpty());
	}

	@Test
	public void testToString() {
		final Queue<String> queue = new Queue<>();
		MatcherAssert.assertThat(queue.toString(), is("[]"));
		queue.add("foo");
		MatcherAssert.assertThat(queue.toString(), is("[foo]"));
		queue.add("bar");
		MatcherAssert.assertThat(queue.toString(), is("[foo, bar]"));
		queue.remove();
		MatcherAssert.assertThat(queue.toString(), is("[bar]"));
	}
}
