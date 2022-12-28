package com.ruoyi.common.annotation;


import com.ruoyi.enums.core.Operator;

import java.lang.annotation.*;


/**
 * @author Class
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
public @interface Condition {
    String field() default "";

    Operator type() default Operator.EQ;

    //对一些非表内字段进行查询筛选
    boolean isField() default true;
}
