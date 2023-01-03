package com.ruoyi.manage.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.core.domain.AssignBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

/**
 * @Author: Class
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "设备", description = "设备信息实体")
@TableName("MQTT_EQUIPMENT")
public class MqttEquipmentEntity extends AssignBaseEntity {
    @ApiModelProperty(value = "设备编号", required = true)
    @TableField("EQUIPMENT_NO")
    @NotBlank(message = "设备编号不能为空")
    private String equipmentNo;

    @ApiModelProperty(value = "设备作用")
    @TableField("EQUIPMENT_EFFECT")
    private String equipmentEffect;

    @ApiModelProperty(value = "设备位置")
    @TableField("EQUIPMENT_POSITION")
    private String equipmentPosition;

    @ApiModelProperty("设备类型 关联字典表：mqtt_equipment_type")
    @TableField("EQUIPMENT_TYPE")
    private String equipmentType;

    @ApiModelProperty(value = "所属部门", readOnly = true)
    @TableField("DEPT_ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long deptId;

    @ApiModelProperty(value = "设备是否启用 关联字典表：mqtt_equipment_status")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty(value = "设备是否在线 关联字典表：mqtt_equipment_online")
    @TableField("ONLINE_STATUS")
    private String onlineStatus;

    @ApiModelProperty(value = "进行绑定的交换机id")
    @TableField("EXCHANGE_ID")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Long exchangeId;

    @ApiModelProperty("备注")
    @TableField("REMARK")
    private String remark;
}
