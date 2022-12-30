package com.ruoyi.manage.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.ruoyi.common.core.service.BaseServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.manage.domain.entity.MqttEquipmentEntity;
import com.ruoyi.manage.domain.entity.MqttEquipmentExchangeEntity;
import com.ruoyi.manage.domain.entity.MqttExchangeEntity;
import com.ruoyi.manage.mapper.MqttEquipmentExchangeMapper;
import com.ruoyi.manage.mapper.MqttEquipmentMapper;
import com.ruoyi.manage.mapper.MqttExchangeMapper;
import com.ruoyi.manage.service.MqttEquipmentExchangeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ruoyi.common.constant.ErrorCodeConstants.EQUIPMENT_BINDING_EXCHANGE;
import static com.ruoyi.common.constant.ErrorCodeConstants.NOT_EXCHANGE_ROLE;

/**
 * @Author: Class
 */
@Service
public class MqttEquipmentExchangeServiceImpl extends BaseServiceImpl<MqttEquipmentExchangeMapper, MqttEquipmentExchangeEntity>
        implements MqttEquipmentExchangeService {
    @Resource
    private MqttEquipmentExchangeMapper mqttEquipmentExchangeMapper;
    @Resource
    private MqttEquipmentMapper mqttEquipmentMapper;
    @Resource
    private MqttExchangeMapper mqttExchangeMapper;

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
        MqttEquipmentExchangeEntity one = new LambdaQueryChainWrapper<>(mqttEquipmentExchangeMapper)
                .eq(MqttEquipmentExchangeEntity::getEquipmentNo, equipmentNo)
                .one();
        // 说明已被绑定
        if (StringUtils.isNotNull(one)) {
            throw exception(EQUIPMENT_BINDING_EXCHANGE, equipmentNo);
        }
        // 如果不存在就判断操作人是否与设备、交换机是否为同一部门
        MqttEquipmentEntity equipmentEntity = new LambdaQueryChainWrapper<>(mqttEquipmentMapper)
                .eq(MqttEquipmentEntity::getEquipmentNo, equipmentNo)
                .one();
        MqttExchangeEntity exchangeEntity = new LambdaQueryChainWrapper<>(mqttExchangeMapper)
                .eq(MqttExchangeEntity::getId, id)
                .one();
        if (!deptId.equals(equipmentEntity.getDeptId()) || !deptId.equals(exchangeEntity.getDeptId())) {
            throw exception(NOT_EXCHANGE_ROLE, exchangeEntity.getExchangeName());
        }
    }
}
