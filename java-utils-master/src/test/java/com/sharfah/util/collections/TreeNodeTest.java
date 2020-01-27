package com.sharfah.util.collections;

import static org.hamcrest.CoreMatchers.is;

import java.util.stream.Collectors;

import org.hamcrest.MatcherAssert;
import org.junit.Test;

public class TreeNodeTest {

	@Test
	public void testStream() {
		final TreeNode<String> root = new TreeNode<>("Root");
		final TreeNode<String> a = root.addChild("A");
		a.addChild("A1");
		a.addChild("A2");
		root.addChild("B");
		final TreeNode<String> c = root.addChild("C");
		c.addChild("C1");

		MatcherAssert.assertThat(root.stream().count(), is(7L));
		MatcherAssert.assertThat(root.stream().map(TreeNode::getData).collect(Collectors.joining(",")),
				is("Root,A,A1,A2,B,C,C1"));
	}

}
