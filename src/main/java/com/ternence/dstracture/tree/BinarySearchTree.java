package com.ternence.dstracture.tree;

import com.ternence.dstracture.tree.exception.UnderflowException;
import lombok.*;

/**
 * 二叉查找树的实现
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/4/30 18:12
 */
public class BinarySearchTree<E extends Comparable<? super E>> {
    private BinaryNode<E> root;

    public BinarySearchTree(E root) {
        this(root, null, null);
    }

    public BinarySearchTree(E element, BinaryNode<E> lNode, BinaryNode<E> rNode) {
        this.root = new BinaryNode<>(element, lNode, rNode);
    }

    public void makeEmpty() {

        this.root = null;
    }

    public boolean isEmpty() {

        return root == null;
    }

    public boolean contains(E e) {

        return contains(e, root);
    }

    /**
     * 内部判断元素是否包含在指定的查找二叉树中，其基本思想是如果在树T中含有项X的节点则返回true，否则返回false，
     * 如果T是空树返回false，否则如果存储在T的根节点的元素就是X那么返回true，再否则我们就对树的左子树或右子树
     * 进行递归的调用，这依赖于X与存储在T中的项的关系
     *
     * @param e       需要搜索的元素
     * @param subTree 查找二叉树的根
     * @return {@code true}表示包含,否则{@code false}为不包含
     */
    private boolean contains(E e, BinaryNode<E> subTree) {
        if (subTree == null) return false;
        int compareResult = e.compareTo(subTree.element);
        if (compareResult < 0) {
            //搜索左子树
            return contains(e, subTree.left);
        } else if (compareResult > 0) {
            //搜索右子树
            return contains(e, subTree.right);
        }
        return true;
    }

    public void insert(E element) {

        root = insert(element, root);
    }

    /**
     * 插入一个新的元素到子树中，基本思想是
     * 1：递归过程中何时创建一个子元素，那肯定是递归到叶子节点的时候，此时再往下就会遇到null节点，那么插入的元素就是要
     * 放在这里，所以new一个新元素
     * 2：回溯到叶子节点的时候将新建的返回的元素赋值给合适的子树，取决于元素与当前节点的大小关系
     * 3：完成插入
     * <p>
     * 递归占用的栈空间的量也只不过为O(log N),所以是足够的，因为这个增长率很慢，
     * <p>
     * 当然如果在进行了N平方次的交叉删除和插入之后这棵树变得很不平衡，那么此时再做插入操作可能就会变成O(N)的时间
     * 复杂度，这将会使树变成一个线性表，这不是我们想要的，所以后面会使用AVL树的思想解决这个问题，不过现实中我们不会使用
     * AVL树，一般都会使用红黑树来解决这个问题。
     *
     * @param e       新的元素
     * @param subTree 子树
     * @return 返回插入后的新的子树的根节点
     */
    private BinaryNode<E> insert(E e, BinaryNode<E> subTree) {
        if (subTree == null) {//当递归到叶子节点上为null的子节点时候返回一个新的节点,然后在递归中赋值
            return new BinaryNode<>(e, null, null);
        }
        //比较一下两个对象的大小
        int compareResult = e.compareTo(subTree.element);

        if (compareResult < 0) {
            //插入左子树
            subTree.left = insert(e, subTree.left);
        } else if (compareResult > 0) {
            //插入右子树
            subTree.right = insert(e, subTree.right);
        }
        //相等的情况下不做任何处理,直接丢弃就OK了
        return subTree;
    }

    public void remove(E element) {

        root = remove(element, root);
    }

    /**
     * 内部删除某棵树上包含元素element的节点,返回删除节点之后的新的树
     *
     * @param element 需要删除的元素
     * @param subTree 子树
     * @return 被删除操作修改之后的子树
     */
    private BinaryNode<E> remove(E element, BinaryNode<E> subTree) {

        return null;
    }

    public E findMin() {
        if (isEmpty()) throw new UnderflowException("The tree is empty!");
        return findMin(root).getElement();
    }

    /**
     * 找到树中最小值的节点，这个就很好做了，因为最小值就在树的最左边
     * 只需要依次递归，只要有左子节点就往下查找，直到最后一个叶子节点就是最小的
     *
     * @param subTree 子树
     * @return 包含最小值的节点
     */
    private BinaryNode<E> findMin(BinaryNode<E> subTree) {
        if (subTree == null) return null;
        else if (subTree.left == null) return subTree;
        //递归遍历左子树
        return findMin(subTree.left);
    }

    public E findMax() {
        if (isEmpty()) throw new UnderflowException("The tree is empty!");
        return findMax(root).getElement();
    }

    /**
     * 找到树中最大值的节点，这个就很好做了，因为最大值就在树的最右边
     * 只需要依次递归，只要有右子节点就往下查找，直到最后一个叶子节点就是最大的
     *
     * @param subTree 子树
     * @return 包含最大值的节点
     */
    private BinaryNode<E> findMax(BinaryNode<E> subTree) {
        if (subTree == null) return null;
        else if (subTree.right == null) return subTree;
        //递归遍历左子树
        return findMax(subTree.right);
    }

    /**
     * 遍历树并打印
     */
    public void traverse() {
    }

    @Data
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    @EqualsAndHashCode
    public static final class BinaryNode<T> {
        private T element;
        private BinaryNode<T> left;
        private BinaryNode<T> right;

        public BinaryNode(T element) {
            this.element = element;
        }

    }
}
