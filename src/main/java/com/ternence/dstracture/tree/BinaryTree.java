package com.ternence.dstracture.tree;

import lombok.*;

import java.util.Stack;

/**
 * 二叉树的实现
 * 定义:
 * 二叉树是一棵树，其中每个节点都不能有多于两个的儿子节点
 * 性质:
 * 1:二叉树的一个性质是一棵平均二叉树的深度要比节点个数N小得多，这个性质有时很重要，分析表明其平均深度为O(√N)
 * 而对于特殊的二叉树即二叉查找树其平均深度为O(log N),(深度为...表示的是这棵树的深度的增长速度和这个函数的一个比较)，
 * 但是不幸的是这棵树的最大深度可以到达N-1(一棵树是N个节点和N-1条边的集合)，后面会给出这个问题的解决方案。
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/4/30 11:36
 */
public class BinaryTree {
    private TreeNode theTree;

    /**
     * 默认的无参构造器,先构造一棵空树
     */
    public BinaryTree(TreeNode root) {
        theTree = root;
    }

    /**
     * 创建一颗测试使用的二叉树
     *
     * @param root 根节点
     * @return 返回当前创建的二叉树
     */
    public static BinaryTree createATestTree(TreeNode root) {
        if (root == null) throw new NullPointerException("根节点不能为null");
        TreeNode nodeB = new TreeNode("B");
        root.setLeft(nodeB);
        TreeNode nodeC = new TreeNode("C");
        root.setRight(nodeC);
        TreeNode nodeD = new TreeNode("D");
        root.getLeft().setLeft(nodeD);
        TreeNode nodeE = new TreeNode("E");
        root.getLeft().setRight(nodeE);
        TreeNode nodeF = new TreeNode("F");
        root.getRight().setLeft(nodeF);
        TreeNode nodeG = new TreeNode("G");
        root.getRight().setRight(nodeG);
        return new BinaryTree(root);
    }

    public TreeNode getRoot() {

        return theTree;
    }

    /**
     * 计算当前树的节点个数
     *
     * @return 节点个数
     */
    public int size() {

        return size(getRoot());
    }

    /**
     * 递归计算每个子树的元素个数,节点为null的子树size为0,节点不为null的子树节点个数为
     * (自身 + 左子树节点个数 + 右子树节点个数),使用递归调用计算size,计算了左子树的节点个数之后返回,
     * 然后继续计算右子树的节点个数后返回，然后相加他们的节点个数之和，然后返回按照以上顺序继续计算，直到计算完毕所有
     *
     * @param subTree 子树的根节点
     * @return 树的所有节点个数
     */
    private int size(TreeNode subTree) {
        if (subTree == null) {
            return 0;
        } else {
            return 1 + size(subTree.getLeft()) + size(subTree.getRight());
        }
    }

    /**
     * 计算树的高度,树的高度等于他的根节点的高度，某个节点的高度为节点本身到一片树叶的最长路径的长
     * <p>
     * 递归计算根节点的左右子树的高度，然后用较大者加1得到整棵树的高度，这个1是最高的这棵子树到根节点的那一条边
     * ,递归结束的条件是遇到叶子节点的时候，遇到叶子节点的时候就开始回溯，叶子节点的高度为0，回溯一次加1得到当前子树的高度，
     * 直到按照 左-右-根 的顺序回到根节点之后就计算出来了树的高度
     *
     * @return 树的高度
     */
    public int height() {

        return height(getRoot());
    }

    private int height(TreeNode subTree) {
        if (subTree == null) {
            return 0;
        } else {
            if (subTree.getLeft() == null && subTree.getRight() == null) {
                return 0;//叶子节点高度为0,而不能是1
            }
            //递归计算左右子树的深度
            int leftOfHeight = height(subTree.getLeft());
            int rightOfHeight = height(subTree.getRight());
            return (leftOfHeight < rightOfHeight) ? rightOfHeight + 1 : leftOfHeight + 1;
        }
    }

    /**
     * 树的深度，一棵树的深度等于他的最深的树叶的深度，该深度等于这棵树的高
     *
     * @return 该树的深度
     */
    public int depth() {

        return height();
    }

    /**
     * 计算某个节点的深度
     *
     * @param theNode 特定的节点
     * @return 某个节点的深度
     */
    public int depth(TreeNode theNode) {
        if (theNode == null) {
            return 0;
        }
        int depth = 0;
        //找到parent节点之后,一次查找parent的parent节点，每找到一个就加一，直到根节点之后parent为null为止
        TreeNode parent = theNode;
        while ((parent = parent(parent)) != null) {
            depth = depth + 1;
        }
        return depth;
    }

    /**
     * 寻找某个节点的双亲节点,如果是null或者根节点那么双亲节点就是null,否则去整棵树中递归搜索双亲节点
     *
     * @param node 特定的节点
     * @return 双亲节点
     */
    public TreeNode parent(TreeNode node) {

        return (node == null || node == getRoot()) ? null : parent(getRoot(), node);
    }

    /**
     * 在某棵子树中去递归的搜索某个节点，如果subTree是root那么就是搜索整棵树
     *
     * @param subTree 特定的子树
     * @param node    子树中某个节点
     * @return 双亲节点, 如果没有找到就是{@code null}
     */
    private TreeNode parent(TreeNode subTree, TreeNode node) {
        if (subTree == null) {
            return null;
        }
        if (subTree.getLeft() == node || subTree.getRight() == node) {
            return subTree;//判断左右节点是否是要查找的节点，这就是需要递归执行的逻辑
        }
        TreeNode parent;
        //递归搜索左子树
        if ((parent = parent(subTree.getLeft(), node)) != null) {
            return parent;
        } else {
            //递归搜索右子树
            return parent(subTree.getRight(), node);
        }
    }

    public void traverse() {

        traverse(getRoot());
    }

    /**
     * 遍历子树的所有节点
     *
     * @param subTree 子树的根节点
     */
    private void traverse(TreeNode subTree) {
        if (subTree != null) {
            System.out.print("[" + subTree.getElement() + " depth:" + depth(subTree) + "] ");
            traverse(subTree.getLeft());
            traverse(subTree.getRight());
        }
    }

    /**
     * 前序遍历方式遍历某一棵子树
     *
     * @param subTree 特定的子树
     */
    public void preOrderTraverse(TreeNode subTree) {
        if (subTree != null) {
            visited(subTree);
            preOrderTraverse(subTree.getLeft());
            preOrderTraverse(subTree.getRight());
        }
    }

    /**
     * 中遍历方式遍历某一棵子树
     *
     * @param subTree 特定的子树
     */
    public void inOrderTraverse(TreeNode subTree) {
        if (subTree != null) {
            inOrderTraverse(subTree.getLeft());
            visited(subTree);
            inOrderTraverse(subTree.getRight());
        }
    }

    /**
     * 后序遍历方式遍历某一棵子树
     *
     * @param subTree 特定的子树
     */
    public void postOrderTraverse(TreeNode subTree) {
        if (subTree != null) {
            postOrderTraverse(subTree.getLeft());
            postOrderTraverse(subTree.getRight());
            visited(subTree);
        }
    }

    /**
     * 是否是一棵空树
     *
     * @return 返回{@code true}表示树为null,否则返回{@code false}表示树为真
     */
    public boolean isEmpty() {

        return getRoot() == null;
    }

    /**
     * 非递归方式的前序遍历
     *
     * @param subTree 特定的子树
     */
    public void nonRecursionPreOrderTraverse(TreeNode subTree) {
        Stack<TreeNode> stack = new Stack<>();//同步的,很低效

    }

    /**
     * 非递归方式的中遍历
     *
     * @param subTree 特定的子树
     */
    public void nonRecursionInOrderTraverse(TreeNode subTree) {

    }

    /**
     * 非递归方式的后序遍历
     *
     * @param subTree 特定的子树
     */
    public void nonRecursionPostOrderTraverse(TreeNode subTree) {

    }

    /**
     * 查看节点的值，并以格式化的方式打印
     *
     * @param node 节点
     */
    private void visited(TreeNode node) {
        if (node != null) {
            System.out.print("[" + node.getElement() + "] ");
        }
    }

    /**
     * 树的节点
     */
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static final class TreeNode {
        /**
         * 当前节点的值
         */
        Object element;
        /**
         * 右子树
         */
        TreeNode right;
        /**
         * 左子树
         */
        TreeNode left;

        public TreeNode(Object element) {
            this.element = element;
        }
    }

    @Override
    public String toString() {

        return theTree.toString();
    }
}
