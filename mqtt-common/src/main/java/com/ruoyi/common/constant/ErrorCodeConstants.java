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

    // ========== 设备模块 1002001000 ==========
    ErrorCode EQUIPMENT_NO_EXISTS = new ErrorCode(1002001000, "设备名称已存在");
    ErrorCode EQUIPMENT_NO_ENABLE = new ErrorCode(1002001001, "设备已被启用");
    ErrorCode EQUIPMENT_BINDING_EXCHANGE = new ErrorCode(1002001002, "该设备已被绑定");
    ErrorCode NOT_EXCHANGE_ROLE = new ErrorCode(1002001003, "交换机不存在或无权限绑定");
}
