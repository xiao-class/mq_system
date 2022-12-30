package com.ruoyi.manage.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.ruoyi.common.constant.ExchangeConstants;
import com.ruoyi.common.core.service.BaseServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.manage.domain.entity.MqttEquipmentEntity;
import com.ruoyi.manage.mapper.MqttEquipmentMapper;
import com.ruoyi.manage.service.MqttEquipmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ruoyi.common.constant.ErrorCodeConstants.EQUIPMENT_NO_ENABLE;
import static com.ruoyi.common.constant.ErrorCodeConstants.EQUIPMENT_NO_EXISTS;

/**
 * @Author: Class
 */
@Service
public class MqttEquipmentServiceImpl extends BaseServiceImpl<MqttEquipmentMapper, MqttEquipmentEntity>
        implements MqttEquipmentService {

    @Resource
    private MqttEquipmentMapper mqttEquipmentMapper;

    /**
     * 判断设备编号是否存在
     *
     * @param id          设备id
     * @param equipmentNo 设备编号
     */
    @Override
    public void checkEquipmentNoUnique(Long id, String equipmentNo) {
        if (StringUtils.isNull(equipmentNo)) {
            return;
        }
        // 通过设备编号查找详细信息
        MqttEquipmentEntity one = new LambdaQueryChainWrapper<>(mqttEquipmentMapper)
                .select(MqttEquipmentEntity::getId)
                .eq(MqttEquipmentEntity::getEquipmentNo, equipmentNo)
                .one();
        if (StringUtils.isNull(one)) {
            return;
        }
        // 如果id不存在说明该设备编号已存在
        if (StringUtils.isNull(id)) {
            throw exception(EQUIPMENT_NO_EXISTS, equipmentNo);
        }
        // 如果id存在但是不相等
        if (!id.equals(one.getId())) {
            throw exception(EQUIPMENT_NO_EXISTS, equipmentNo);
        }
    }

    /**
     * 根据设备id判断该设备是否被启用
     *
     * @param id 设备id
     */
    @Override
    public void checkEquipmentStatus(Long id) {
        // 判断编号是否存在
        if (StringUtils.isNull(id)) {
            return;
        }
        // 根据ID查询信息
        MqttEquipmentEntity one = new LambdaQueryChainWrapper<>(mqttEquipmentMapper)
                .select(MqttEquipmentEntity::getEquipmentNo, MqttEquipmentEntity::getStatus)
                .eq(MqttEquipmentEntity::getId, id)
                .one();
        // 判断是否为空
        if (StringUtils.isNull(one)) {
            return;
        }
        // 是否启用
        if (ExchangeConstants.EXCHANGE_EQUIPMENT_ENABLE.equals(one.getStatus())) {
            throw exception(EQUIPMENT_NO_ENABLE);
        }
    }
}
