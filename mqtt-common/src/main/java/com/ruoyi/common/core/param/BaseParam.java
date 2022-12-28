package com.ruoyi.common.core.param;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.annotation.Condition;
import com.ruoyi.common.utils.WrapperUtil;
import com.ruoyi.enums.core.Operator;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.SneakyThrows;
import org.apache.commons.compress.utils.Lists;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author Class
 * 查询列表参数基类
 */
@Data
@ApiModel("查询列表参数")
public class BaseParam<T> {
    public QueryWrapper<T> wrapper() {
        return wrapper(true);
    }

    @SneakyThrows
    @SuppressWarnings({"all"})
    public QueryWrapper<T> wrapper(boolean getSuper) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        Class<? extends BaseParam> aClass = this.getClass();
        List<Field> fieldList;
        if (getSuper) {
            fieldList = WrapperUtil.getAllField(aClass);
        } else {
            fieldList = Arrays.asList(aClass.getDeclaredFields());
        }
        for (Field field : fieldList) {
            if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers()) || Collection.class.isAssignableFrom(field.getType())) { //如果是static
                continue;
            }
            Condition condition = field.getDeclaredAnnotation(Condition.class);
            String fieldName;
            String getMethodName;
            if (condition != null) {
                if (!condition.isField()) {
                    continue;
                }
                Operator type = condition.type();
                String conditionField = condition.field();
                fieldName = field.getName();
                getMethodName = "get" + StrUtil.upperFirst(fieldName);
                Object value = ReflectUtil.invoke(this, getMethodName);
                if (ObjectUtil.isNotEmpty(value)) {
                    WrapperFunction function = type.getFunction();
                    if (type == Operator.IN || type == Operator.NOT_IN) {
                        ArrayList<Object> valueList = Lists.newArrayList();
                        valueList.add(value);
                        function.wrapper(queryWrapper, conditionField, valueList);
                    } else {
                        function.wrapper(queryWrapper, conditionField, value);
                    }
                }
            } else {
                fieldName = field.getName();
                getMethodName = "get" + StrUtil.upperFirst(fieldName);
                fieldName = StrUtil.toUnderlineCase(fieldName);
                Object value = ReflectUtil.invoke(this, getMethodName);
                if (ObjectUtil.isNotEmpty(value)) {
                    WrapperFunction function = Operator.EQ.getFunction();
                    function.wrapper(queryWrapper, fieldName, value);
                }
            }
        }
        return queryWrapper;
    }

}
