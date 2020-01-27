package com.sharfah.util.collections;

import static org.hamcrest.CoreMatchers.is;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class QueueWithStacksTest {

	@Test
	public void testPeek() {
		final QueueWithStacks<String> q = new QueueWithStacks<>();
		MatcherAssert.assertThat(q.isEmpty(), is(true));
		q.add("foo");
		MatcherAssert.assertThat(q.peek(), is("foo"));
		q.add("bar");
		q.add("baz");
		MatcherAssert.assertThat(q.peek(), is("foo"));
	}

	@Test
	public void testRemove() {
		final QueueWithStacks<String> q = new QueueWithStacks<>();
		q.add("foo");
		q.add("bar");
		q.add("baz");
		MatcherAssert.assertThat(q.remove(), is("foo"));
		MatcherAssert.assertThat(q.remove(), is("bar"));
		MatcherAssert.assertThat(q.remove(), is("baz"));
		MatcherAssert.assertThat(q.isEmpty(), is(true));
	}
}
