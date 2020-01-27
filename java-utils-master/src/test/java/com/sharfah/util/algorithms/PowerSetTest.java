package com.sharfah.util.algorithms;

import static com.sharfah.util.algorithms.PowerSet.iterativePowerSet;
import static com.sharfah.util.algorithms.PowerSet.iterativePowerSet2;
import static com.sharfah.util.algorithms.PowerSet.powerSet;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class PowerSetTest {

	@Test
	public void testPowerSet() {

		final List<String> list = new ArrayList<>();
		final List<List<String>> powerSet = new ArrayList<>();
		powerSet.add(Collections.emptyList());
		MatcherAssert.assertThat(powerSet(list), is(powerSet));

		list.add("a");
		powerSet.add(Arrays.asList("a"));
		MatcherAssert.assertThat(powerSet(list), is(powerSet));

		list.add("b");
		list.add("c");
		final List<List<String>> result = powerSet(list);
		MatcherAssert.assertThat(result.size(), is(8));
		assertTrue(result.contains(Collections.emptyList()));
		assertTrue(result.contains(Arrays.asList("a")));
		assertTrue(result.contains(Arrays.asList("b")));
		assertTrue(result.contains(Arrays.asList("c")));
		assertTrue(result.contains(Arrays.asList("a", "b")));
		assertTrue(result.contains(Arrays.asList("b", "c")));
		assertTrue(result.contains(Arrays.asList("a", "c")));
		assertTrue(result.contains(Arrays.asList("a", "b", "c")));
	}

	@Test
	public void testIterativePowerSet() {

		final List<String> list = new ArrayList<>();
		final List<List<String>> powerSet = new ArrayList<>();
		powerSet.add(Collections.emptyList());
		MatcherAssert.assertThat(iterativePowerSet(list), is(powerSet));

		list.add("a");
		powerSet.add(Arrays.asList("a"));
		MatcherAssert.assertThat(iterativePowerSet(list), is(powerSet));

		list.add("b");
		list.add("c");
		final List<List<String>> result = iterativePowerSet(list);
		MatcherAssert.assertThat(result.size(), is(8));
		assertTrue(result.contains(Collections.emptyList()));
		assertTrue(result.contains(Arrays.asList("a")));
		assertTrue(result.contains(Arrays.asList("b")));
		assertTrue(result.contains(Arrays.asList("c")));
		assertTrue(result.contains(Arrays.asList("a", "b")));
		assertTrue(result.contains(Arrays.asList("b", "c")));
		assertTrue(result.contains(Arrays.asList("a", "c")));
		assertTrue(result.contains(Arrays.asList("a", "b", "c")));
	}

	@Test
	public void testIterativePowerSet2() {

		final List<String> list = new ArrayList<>();
		final List<List<String>> powerSet = new ArrayList<>();
		powerSet.add(Collections.emptyList());
		MatcherAssert.assertThat(iterativePowerSet2(list), is(powerSet));

		list.add("a");
		powerSet.add(Arrays.asList("a"));
		MatcherAssert.assertThat(iterativePowerSet2(list), is(powerSet));

		list.add("b");
		list.add("c");
		final List<List<String>> result = iterativePowerSet2(list);
		MatcherAssert.assertThat(result.size(), is(8));
		assertTrue(result.contains(Collections.emptyList()));
		assertTrue(result.contains(Arrays.asList("a")));
		assertTrue(result.contains(Arrays.asList("b")));
		assertTrue(result.contains(Arrays.asList("c")));
		assertTrue(result.contains(Arrays.asList("a", "b")));
		assertTrue(result.contains(Arrays.asList("b", "c")));
		assertTrue(result.contains(Arrays.asList("a", "c")));
		assertTrue(result.contains(Arrays.asList("a", "b", "c")));
	}
}
