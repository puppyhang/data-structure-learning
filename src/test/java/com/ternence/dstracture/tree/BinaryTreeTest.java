package com.ternence.dstracture.tree;

import org.junit.Test;

/**
 * 二叉树测试代码
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/4/30 12:31
 */
public class BinaryTreeTest {
    private BinaryTree.TreeNode root = new BinaryTree.TreeNode("A");
    private BinaryTree binaryTree = BinaryTree.createATestTree(root);

    @Test
    public void traverse() throws Exception {
        binaryTree.traverse();
    }

    @Test
    public void depth() throws Exception {
        System.out.println("树的深度为:" + binaryTree.depth());
    }

    @Test
    public void size() throws Exception {
        System.out.println("元素个数为:" + binaryTree.size());
    }

    @Test
    public void height() throws Exception {
        System.out.println("树的高度为:" + binaryTree.height());
    }

    @Test
    public void preOrderTraverse() throws Exception {
        binaryTree.preOrderTraverse(binaryTree.getRoot());
    }

    @Test
    public void inOrderTraverse() throws Exception {
        binaryTree.inOrderTraverse(binaryTree.getRoot());
    }

    @Test
    public void postOrderTraverse() throws Exception {
        binaryTree.postOrderTraverse(binaryTree.getRoot());
    }

}