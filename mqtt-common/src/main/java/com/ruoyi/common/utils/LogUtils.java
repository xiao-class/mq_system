package com.ruoyi.common.utils;

/**
 * 处理并记录日志文件
 *
 * @author Class
 */
public class LogUtils {
    public static String getBlock(Object msg) {
        if (msg == null) {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
