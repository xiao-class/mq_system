package com.ruoyi.manage.domain.param;

import com.ruoyi.common.annotation.Condition;
import com.ruoyi.common.core.param.BaseParam;
import com.ruoyi.enums.core.Operator;
import com.ruoyi.manage.domain.entity.MqttEquipmentEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Class
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "设备列表查询条件")
public class QueryEquipmentListParam extends BaseParam<MqttEquipmentEntity> {
    @ApiModelProperty("设备编号")
    @Condition(field = "EQUIPMENT_NO", type = Operator.LIKE)
    private String equipmentNo;

    @ApiModelProperty(value = "设备是否启用 关联字典表：mqtt_equipment_status")
    @Condition(field = "STATUS", type = Operator.EQ)
    private String status;

    @ApiModelProperty("设备类型 关联字典表：mqtt_equipment_type")
    @Condition(field = "EQUIPMENT_TYPE", type = Operator.EQ)
    private String equipmentType;

    @ApiModelProperty(value = "设备是否在线 关联字典表：mqtt_equipment_online")
    @Condition(field = "ONLINE_STATUS", type = Operator.EQ)
    private String onlineStatus;
}
