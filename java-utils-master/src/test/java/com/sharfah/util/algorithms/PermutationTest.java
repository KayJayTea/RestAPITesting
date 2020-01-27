package com.sharfah.util.algorithms;

import static com.sharfah.util.algorithms.Permutation.permutations;
import static org.hamcrest.CoreMatchers.hasItems;

import java.util.Arrays;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class PermutationTest {

	@Test
	public void testStringPermutations() {
		MatcherAssert.assertThat(permutations("a"), hasItems("a"));
		MatcherAssert.assertThat(permutations("ab"), hasItems("ab", "ba"));
		MatcherAssert.assertThat(permutations("abc"), hasItems("abc", "acb", "bac", "bca", "cab", "cba"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testListPermutations() {
		MatcherAssert.assertThat(permutations(Arrays.asList("a")), hasItems(Arrays.asList("a")));
		MatcherAssert.assertThat(permutations(Arrays.asList("a", "b", "c")),
				hasItems(Arrays.asList("a", "b", "c"), Arrays.asList("a", "c", "b"), Arrays.asList("b", "a", "c"),
						Arrays.asList("b", "c", "a"), Arrays.asList("c", "b", "a"), Arrays.asList("c", "a", "b")));
	}

}
