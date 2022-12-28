package com.ruoyi.common.core.param;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author Class
 */
@FunctionalInterface
public interface WrapperFunction {
    @SuppressWarnings("all")
    void wrapper(QueryWrapper wrapper, String name, Object value);
}
