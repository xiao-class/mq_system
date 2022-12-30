package com.ruoyi.manage.service.impl;

import com.ruoyi.common.core.service.BaseServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.manage.domain.entity.MqttEquipmentExchangeEntity;
import com.ruoyi.manage.mapper.MqttEquipmentExchangeMapper;
import com.ruoyi.manage.service.MqttEquipmentExchangeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Class
 */
@Service
public class MqttEquipmentExchangeServiceImpl extends BaseServiceImpl<MqttEquipmentExchangeMapper, MqttEquipmentExchangeEntity>
        implements MqttEquipmentExchangeService {
    @Resource
    private MqttEquipmentExchangeMapper mqttEquipmentExchangeMapper;

    /**
     * 判断该设备是否已经与交换机进行绑定(抛出异常)
     *
     * @param id          交换机id
     * @param equipmentNo 设备编号
     * @param deptId      操作人部门
     */
    @Override
    public void checkEquipmentNoBinDing(Long id, String equipmentNo, Long deptId) {
        // 判断是否为空
        if (StringUtils.isEmpty(equipmentNo)) {
            return;
        }
        // 根据设备编号查询是否已经被绑定

    }
}
