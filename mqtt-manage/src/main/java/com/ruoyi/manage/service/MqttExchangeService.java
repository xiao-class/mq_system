package com.ruoyi.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.manage.domain.entity.MqttExchangeEntity;

/**
 * @Author: Class
 */
public interface MqttExchangeService extends IService<MqttExchangeEntity> {
    /**
     * 校验交换机名称是否唯一
     *
     * @param id           交换机id
     * @param exchangeName 交换机名称
     */
    void checkExchangeNameUnique(Long id, String exchangeName);

    /**
     * 根据交换机id判断该交换机是否启用
     *
     * @param id 交换机id
     */
    void checkExchangeStatus(Long id);
}
