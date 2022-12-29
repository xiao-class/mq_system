package com.ruoyi.common.exception.entity;

import lombok.Data;

/**
 * @Author: Class
 */
@Data
public class ErrorCode {
    /**
     * 错误码
     */
    private final Integer code;
    /**
     * 错误提示
     */
    private final String msg;

    public ErrorCode(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }
}
