package com.ruoyi.common.constant;

import com.ruoyi.common.exception.entity.ErrorCode;

/**
 * @Author: Class
 * System 错误码枚举类
 * <p>
 * system 系统，使用 1-002-000-000 段
 */
public interface ErrorCodeConstants {
    // ========== 交换机（EXCHANGE） 模块 1002000000 ==========
    ErrorCode EXCHANGE_NAME_EXISTS = new ErrorCode(1002000000, "交换机名称已存在");
    ErrorCode EXCHANGE_NAME_ENABLE = new ErrorCode(1002000001, "交换机已被启用");
}