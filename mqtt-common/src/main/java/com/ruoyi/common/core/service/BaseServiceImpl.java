package com.ruoyi.common.core.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
