package com.ruoyi.common.core.domain;

import com.ruoyi.common.constant.HttpStatus;
import lombok.Data;

import java.io.Serializable;

@Data
public class DataModel<T> implements Serializable {
    private Integer code;
    private String msg;
    private T data;

    public DataModel() {
    }

    public DataModel(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public DataModel(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public DataModel(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static <T> DataModel<T> success() {
        return new DataModel<>(HttpStatus.SUCCESS, "操作成功");
    }


    public static <T> DataModel<T> success(T data) {
        return new DataModel<>(HttpStatus.SUCCESS, data);
    }


    public static <T> DataModel<T> success(String msg) {
        return new DataModel<>(HttpStatus.SUCCESS, msg);
    }

    public static <T> DataModel<T> success(String msg, T data) {
        return new DataModel<>(HttpStatus.SUCCESS, msg, data);
    }


    public static <T> DataModel<T> error() {
        return new DataModel<>(HttpStatus.ERROR, "操作失败");
    }


    public static <T> DataModel<T> error(T data) {
        return new DataModel<>(HttpStatus.ERROR, data);
    }


    public static <T> DataModel<T> error(String msg) {
        return new DataModel<>(HttpStatus.ERROR, msg);
    }

    public static <T> DataModel<T> error(Integer code, String msg) {
        return new DataModel<>(code, msg);
    }

    public static <T> DataModel<T> error(String msg, T data) {
        return new DataModel<>(HttpStatus.ERROR, msg, data);
    }

    public static <T> DataModel<T> error(Integer code, String msg, T data) {
        return new DataModel<>(code, msg, data);
    }

    public static <T> DataModel<T> sensitive(String msg) {
        return new DataModel<>(HttpStatus.EXIST_SENSITIVE, msg);
    }

}
