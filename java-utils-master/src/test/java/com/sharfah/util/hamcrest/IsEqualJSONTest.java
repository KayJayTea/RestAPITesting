package com.sharfah.util.hamcrest;

import static com.sharfah.util.hamcrest.IsEqualJSON.equalToJSON;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class IsEqualJSONTest {

	@Test
	public void test() {
		MatcherAssert.assertThat(new String[] { "foo", "bar" }, equalToJSON("[\"foo\", \"bar\"]"));
	}

	@Test(expected = AssertionError.class)
	public void testFailure() {
		MatcherAssert.assertThat(new String[] { "foo", "bar" }, equalToJSON("[\"foo2\", \"bar\"]"));
	}
}
