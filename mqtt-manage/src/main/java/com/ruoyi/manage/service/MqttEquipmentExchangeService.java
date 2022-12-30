package com.ruoyi.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.manage.domain.entity.MqttEquipmentExchangeEntity;

/**
 * @Author: Class
 */
public interface MqttEquipmentExchangeService extends IService<MqttEquipmentExchangeEntity> {
    /**
     * 判断该设备是否已经与交换机进行绑定(抛出异常)
     *
     * @param id          交换机id
     * @param equipmentNo 设备编号
     * @param deptId      操作人部门
     */
    void checkEquipmentNoBinDing(Long id, String equipmentNo, Long deptId);
}
