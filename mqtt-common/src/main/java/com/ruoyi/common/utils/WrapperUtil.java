package com.ruoyi.common.utils;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Author: Class
 */
public class WrapperUtil {

    private static boolean isSimpleType(Class<?> clazz) {
        if (clazz == null) {
            return false;
        }
        if (String.class.getTypeName().equals(clazz.getTypeName())) {
            return true;
        } else if (Date.class.getTypeName().equals(clazz.getTypeName())) {
            return true;
        } else if (LocalDateTime.class.getTypeName().equals(clazz.getTypeName())) {
            return true;
        } else if (LocalDate.class.getTypeName().equals(clazz.getTypeName())) {
            return true;
        } else {
            return ClassUtil.isBasicType(clazz);
        }
    }

    public static <T> QueryWrapper<T> wrapper(T obj) {
        return wrapper(obj, false);
    }

    public static <T> QueryWrapper<T> wrapper(T obj, Boolean getSuper) {
        if (obj == null || "".equals(obj)) {
            return new QueryWrapper<>();
        }
        Class<?> clazz = obj.getClass();
        List<Field> fieldList = getSuper ? getAllField(clazz) : Arrays.asList(clazz.getDeclaredFields());
        return wrapperByFieldList(obj, fieldList);
    }

    public static List<Field> getAllField(Class clazz) {
        List<Field> fieldList = new ArrayList<>();
        while (clazz != null && !clazz.getName().toLowerCase().equals("java.lang.object")) {
            fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
            clazz = clazz.getSuperclass();
        }
        return fieldList;
    }

    /**
     * 根据参数列表获取QueryWrapper
     *
     * @param obj       对应实体表
     * @param fieldList 参数对象列表
     * @param <T>
     * @return
     */
    public static <T> QueryWrapper<T> wrapperByFieldList(T obj, List<Field> fieldList) {
        QueryWrapper<T> qw = new QueryWrapper<>();
        for (Field field : fieldList) {
            if (Modifier.isStatic(field.getModifiers()) || Modifier.isFinal(field.getModifiers())) {
                continue;
            }
            if (!isSimpleType(field.getType())) {
                continue;
            }
            String fieldName = field.getName();
            String getMethodName = "get" + StrUtil.upperFirst(fieldName);
            String columnName = StrUtil.toUnderlineCase(fieldName);
            TableField tableField = field.getAnnotation(TableField.class);
            TableId tableId = field.getAnnotation(TableId.class);
            if (tableField != null) {
                if (!tableField.exist()) {
                    continue;
                }
                if (StrUtil.isNotEmpty(tableField.value())) {
                    columnName = tableField.value();
                }
            } else if (tableId != null) {
                if (StrUtil.isNotEmpty(tableId.value())) {
                    columnName = tableId.value();
                }
            }
            Object ret = ReflectUtil.invoke(obj, getMethodName);
            if (null != ret && StringUtils.isNotEmpty(ret.toString())) {
                if (ret instanceof String) {
                    qw.like(columnName, ret);
                } else {
                    qw.eq(columnName, ret);
                }
            }
        }
        return qw;
    }


}
