package com.ruoyi.manage.service.impl;

import com.ruoyi.common.core.service.BaseServiceImpl;
import com.ruoyi.manage.domain.entity.MqttExchangeEntity;
import com.ruoyi.manage.mapper.MqttExchangeMapper;
import com.ruoyi.manage.service.MqttExchangeService;
import org.springframework.stereotype.Service;

/**
 * @Author: zk
 */
@Service("mqttExchangeService")
public class MqttExchangeServiceImpl extends BaseServiceImpl<MqttExchangeMapper, MqttExchangeEntity>
        implements MqttExchangeService {
}
