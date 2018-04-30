package com.ternence.dstracture.tree.exception;

/**
 * 在树中不包含任何元素的时候执行查找最值操作将会抛出这个异常
 *
 * @author Ternence
 * @version 1.0
 * @since 2018/4/30 20:47
 */
public class UnderflowException extends RuntimeException {
    public UnderflowException() {
        super();
    }

    public UnderflowException(String message) {
        super(message);
    }
}
