package com.sharfah.util.collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

public class BinaryTreeTest {

	BinaryTreeNode<Integer> root;
	BinaryTreeNode<Integer> n11;
	BinaryTreeNode<Integer> n12;
	BinaryTreeNode<Integer> n21;
	BinaryTreeNode<Integer> n22;
	BinaryTreeNode<Integer> n23;

	@Before
	public void setUp() {
		root = new BinaryTreeNode<>(8);
		n11 = new BinaryTreeNode<>(4);
		n12 = new BinaryTreeNode<>(10);
		n21 = new BinaryTreeNode<>(2);
		n22 = new BinaryTreeNode<>(6);
		n23 = new BinaryTreeNode<>(20);
		root.setLeft(n11);
		root.setRight(n12);
		n11.setLeft(n21);
		n11.setRight(n22);
		n12.setRight(n23);
	}

	@Test
	public void testInOrderTraversal() {
		final List<Integer> list = new ArrayList<>();
		BinaryTree.inOrderTraversal(root, list::add);
		MatcherAssert.assertThat(list, is(Arrays.asList(2, 4, 6, 8, 10, 20)));
	}

	@Test
	public void testPreOrderTraversal() {
		final List<Integer> list = new ArrayList<>();
		BinaryTree.preOrderTraversal(root, list::add);
		MatcherAssert.assertThat(list, is(Arrays.asList(8, 4, 2, 6, 10, 20)));
	}

	@Test
	public void testPostOrderTraversal() {
		final List<Integer> list = new ArrayList<>();
		BinaryTree.postOrderTraversal(root, list::add);
		MatcherAssert.assertThat(list, is(Arrays.asList(2, 6, 4, 20, 10, 8)));
	}

	@Test
	public void testLevelOrderTraversal() {
		final List<Integer> list = new ArrayList<>();
		BinaryTree.levelOrderTraversal(root, list::add);
		MatcherAssert.assertThat(list, is(Arrays.asList(8, 4, 10, 2, 6, 20)));
	}

	@Test
	public void testCreateFromArray() {
		BinaryTreeNode<Integer> node = BinaryTree.createFromArray(Arrays.asList(1, 2, 3));
		MatcherAssert.assertThat(node.getData(), is(2));
		List<Integer> list = new ArrayList<>();
		BinaryTree.inOrderTraversal(node, list::add);
		MatcherAssert.assertThat(list, is(Arrays.asList(1, 2, 3)));

		node = BinaryTree.createFromArray(Arrays.asList(1, 2, 3, 4, 5, 6));
		MatcherAssert.assertThat(node.getData(), is(4));
		MatcherAssert.assertThat(node.getLeft().getData(), is(2));
		MatcherAssert.assertThat(node.getLeft().getLeft().getData(), is(1));
		MatcherAssert.assertThat(node.getLeft().getRight().getData(), is(3));
		MatcherAssert.assertThat(node.getRight().getData(), is(6));
		MatcherAssert.assertThat(node.getRight().getLeft().getData(), is(5));
		list = new ArrayList<>();
		BinaryTree.inOrderTraversal(node, list::add);
		MatcherAssert.assertThat(list, is(Arrays.asList(1, 2, 3, 4, 5, 6)));
	}

	@Test
	public void testNodesByLevel() {
		final Map<Integer, List<Integer>> nodesByLevel = BinaryTree.nodesByLevel(root);
		MatcherAssert.assertThat(nodesByLevel.get(0), is(Arrays.asList(8)));
		MatcherAssert.assertThat(nodesByLevel.get(1), is(Arrays.asList(4, 10)));
		MatcherAssert.assertThat(nodesByLevel.get(2), is(Arrays.asList(2, 6, 20)));
	}

	@Test
	public void testGetHeight() {
		MatcherAssert.assertThat(BinaryTree.getHeight(root), is(2));
		MatcherAssert.assertThat(BinaryTree.getHeight(new BinaryTreeNode<>(4)), is(0));
	}

	@Test
	public void testIsBinarySearchTree() {
		MatcherAssert.assertThat(BinaryTree.isBinarySearchTree(root), is(true));
	}

	@Test
	public void testUnder() {
		MatcherAssert.assertThat(BinaryTree.isUnder(root, n23), is(true));
		MatcherAssert.assertThat(BinaryTree.isUnder(n12, n22), is(false));
	}

	@Test
	public void testFindCommonAncestor() {
		MatcherAssert.assertThat(BinaryTree.findCommonAncestor(root, n22, n23), is(root));
		MatcherAssert.assertThat(BinaryTree.findCommonAncestor(root, n22, n21), is(n11));
	}

	@Test
	public void testTreesEqual() {
		MatcherAssert.assertThat(BinaryTree.treesEqual(root, root), is(true));

		final BinaryTreeNode<Integer> root2 = new BinaryTreeNode<>(8);
		root2.setLeft(new BinaryTreeNode<>(4));
		root2.setRight(new BinaryTreeNode<>(10));
		;
		MatcherAssert.assertThat(BinaryTree.treesEqual(root, root2), is(false));
	}

	@Test
	public void testIsSubTree() {
		MatcherAssert.assertThat(BinaryTree.isSubTree(root, n11), is(true));
		MatcherAssert.assertThat(BinaryTree.isSubTree(root, n22), is(true));
		MatcherAssert.assertThat(BinaryTree.isSubTree(n11, n12), is(false));
	}

	@Test
	public void testGetKthElement() {
		MatcherAssert.assertThat(BinaryTree.getKthElement(root, 0), is(8));
		MatcherAssert.assertThat(BinaryTree.getKthElement(root, 2), is(10));
		MatcherAssert.assertThat(BinaryTree.getKthElement(root, 5), is(20));
		MatcherAssert.assertThat(BinaryTree.getKthElement(root, 6), is(nullValue()));
	}
}
