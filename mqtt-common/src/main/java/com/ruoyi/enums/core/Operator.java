package com.ruoyi.enums.core;

import com.ruoyi.common.core.param.WrapperFunction;

import java.util.Collection;

/**
 * @author Class
 */
@SuppressWarnings("all")
public enum Operator {
    // =
    EQ((wrapper, name, value) -> wrapper.eq(name, value)),

    // <>
    NE((wrapper, name, value) -> wrapper.ne(name, value)),

    // >
    GT((wrapper, name, value) -> wrapper.gt(name, value)),

    // >=
    GE((wrapper, name, value) -> wrapper.ge(name, value)),

    // <
    LT((wrapper, name, value) -> wrapper.lt(name, value)),

    // <=
    LE((wrapper, name, value) -> wrapper.le(name, value)),

    // IN
    IN((wrapper, name, value) -> wrapper.in(name, (Collection<?>) value)),

    // NOT IN
    NOT_IN((wrapper, name, value) -> wrapper.notIn(name, value)),

    // LIKE
    LIKE((wrapper, name, value) -> wrapper.like(name, value)),
    LIKE_LEFT((wrapper, name, value) -> wrapper.likeLeft(name, value)),
    LIKE_RIGHT((wrapper, name, value) -> wrapper.likeRight(name, value)),
    ;

    private WrapperFunction function;

    Operator(WrapperFunction function) {
        this.function = function;
    }

    public WrapperFunction getFunction() {
        return function;
    }

    public void setFunction(WrapperFunction function) {
        this.function = function;
    }
}
