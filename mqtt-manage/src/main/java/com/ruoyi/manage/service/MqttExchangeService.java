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
     * @param mqttExchangeEntity 交换机信息
     * @return 结果
     */
    String checkExchangeNameUnique(MqttExchangeEntity mqttExchangeEntity);

    /**
     * 判断该交换机是否启用
     *
     * @param id 交换机id
     * @return link
     */
    String checkExchangeStatus(Long id);
}
