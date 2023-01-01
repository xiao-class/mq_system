package com.ruoyi.manage.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.core.domain.AssignBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Class
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "设备", description = "设备信息实体")
@TableName("MQTT_EQUIPMENT_EXCHANGE")

public class MqttEquipmentExchangeEntity extends AssignBaseEntity {
    @ApiModelProperty("交换机ID")
    @TableField("EXCHANGE_ID")
    private Long exchangeId;

    @ApiModelProperty("设备编号")
    @TableField("EQUIPMENT_NO")
    private String equipmentNo;
}
