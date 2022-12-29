package com.ruoyi.common.core.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.exception.entity.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * service层通用业务处理
 *
 * @Author: Class
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {
    /**
     * 将日志注入
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 错误码提示模板
     */
    private final ConcurrentMap<Integer, String> MESSAGES = new ConcurrentHashMap<>();

    // ========== 和 ServiceException 的集成 ==========

    public ServiceException exception(ErrorCode errorCode) {
        String messagePattern = MESSAGES.getOrDefault(errorCode.getCode(), errorCode.getMsg());
        return exception0(errorCode.getCode(), messagePattern);
    }

    public ServiceException exception(ErrorCode errorCode, Object... params) {
        String messagePattern = MESSAGES.getOrDefault(errorCode.getCode(), errorCode.getMsg());
        return exception0(errorCode.getCode(), messagePattern, params);
    }

    /**
     * 创建指定编号的 ServiceException 的异常
     *
     * @param code 编号
     * @return 异常
     */
    public ServiceException exception(Integer code) {
        return exception0(code, MESSAGES.get(code));
    }

    /**
     * 创建指定编号的 ServiceException 的异常
     *
     * @param code   编号
     * @param params 消息提示的占位符对应的参数
     * @return 异常
     */
    public ServiceException exception(Integer code, Object... params) {
        return exception0(code, MESSAGES.get(code), params);
    }

    public ServiceException exception0(Integer code, String messagePattern, Object... params) {
        String message = doFormat(code, messagePattern, params);
        return new ServiceException(message, code);
    }

    // ========== 格式化方法 ==========

    /**
     * 将错误编号对应的消息使用 params 进行格式化。
     *
     * @param code           错误编号
     * @param messagePattern 消息模版
     * @param params         参数
     * @return 格式化后的提示
     */
    public String doFormat(int code, String messagePattern, Object... params) {
        StringBuilder sbuf = new StringBuilder(messagePattern.length() + 50);
        int i = 0;
        int j;
        int l;
        for (l = 0; l < params.length; l++) {
            j = messagePattern.indexOf("{}", i);
            if (j == -1) {
                logger.error("[doFormat][参数过多：错误码({})|错误内容({})|参数({})", code, messagePattern, params);
                if (i == 0) {
                    return messagePattern;
                } else {
                    sbuf.append(messagePattern.substring(i));
                    return sbuf.toString();
                }
            } else {
                sbuf.append(messagePattern, i, j);
                sbuf.append(params[l]);
                i = j + 2;
            }
        }
        if (messagePattern.indexOf("{}", i) != -1) {
            logger.error("[doFormat][参数过少：错误码({})|错误内容({})|参数({})", code, messagePattern, params);
        }
        sbuf.append(messagePattern.substring(i));
        return sbuf.toString();
    }

}
