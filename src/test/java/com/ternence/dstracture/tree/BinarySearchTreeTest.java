package com.ternence.dstracture.tree;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 二叉查找树的测试
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/4/30 18:21
 */
public class BinarySearchTreeTest {
    private static BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>(0);

    /**
     * 初始化一颗二叉搜索树
     */
    @BeforeClass
    public static void init() {
        binarySearchTree.insert(-1);
        binarySearchTree.insert(-10);
        binarySearchTree.insert(3);
        binarySearchTree.insert(55);
        binarySearchTree.insert(6);
        binarySearchTree.insert(-50);
    }

    @Test
    public void contains() throws Exception {
        Integer element = 10;

        boolean result = binarySearchTree.contains(element);
        if (result) {
            System.out.println("包含元素" + element);
        } else {
            System.out.println("不包含元素" + element);
        }
    }

    @Test
    public void makeEmpty() throws Exception {
        binarySearchTree.makeEmpty();
        if (binarySearchTree.isEmpty()) {
            System.out.println("空树");
        } else {
            System.out.println("非空");
        }
    }

    @Test
    public void isEmpty() throws Exception {
        if (binarySearchTree.isEmpty()) {
            System.out.println("空树");
        } else {
            System.out.println("非空");
        }
    }

    @Test
    public void insert() throws Exception {
        binarySearchTree.insert(69);
        System.out.println("插入成功");
    }

    @Test
    public void remove() throws Exception {
        binarySearchTree.remove(2);
    }

    @Test
    public void findMin() throws Exception {
        Integer min = binarySearchTree.findMin();
        System.out.println("The min value is " + min);
    }

    @Test
    public void findMax() throws Exception {
        Integer max = binarySearchTree.findMax();
        System.out.println("The max value is " + max);
    }

}