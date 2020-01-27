package com.sharfah.util.algorithms;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class TowersOfHanoiTest {

	@Test
	public void test() {

		final TowersOfHanoi towersOfHanoi = new TowersOfHanoi(1, 2, 3, 4, 5);
		towersOfHanoi.move();

		assertTrue(towersOfHanoi.getTower1().isEmpty());
		assertTrue(towersOfHanoi.getTower2().isEmpty());
		assertFalse(towersOfHanoi.getTower3().isEmpty());
		MatcherAssert.assertThat(towersOfHanoi.getTower3().size(), is(5));
		MatcherAssert.assertThat(towersOfHanoi.getTower3().pop(), is(1));
		MatcherAssert.assertThat(towersOfHanoi.getTower3().pop(), is(2));
		MatcherAssert.assertThat(towersOfHanoi.getTower3().pop(), is(3));
		MatcherAssert.assertThat(towersOfHanoi.getTower3().pop(), is(4));
		MatcherAssert.assertThat(towersOfHanoi.getTower3().pop(), is(5));
	}

}
