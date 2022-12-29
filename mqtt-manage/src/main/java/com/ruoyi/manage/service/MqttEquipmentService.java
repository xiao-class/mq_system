package com.ruoyi.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.manage.domain.entity.MqttEquipmentEntity;

/**
 * @Author: Class
 */
public interface MqttEquipmentService extends IService<MqttEquipmentEntity> {
    /**
     * 判断设备编号是否存在(抛异常)
     *
     * @param id          设备id
     * @param equipmentNo 设备编号
     */
    void checkEquipmentNoUnique(Long id, String equipmentNo);

    /**
     * 根据设备id判断该设备是否被启用(抛异常)
     *
     * @param id 设备id
     */
    void checkEquipmentStatus(Long id);
}
