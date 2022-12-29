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
@ApiModel(value = "交换机", description = "交换机信息实体")
@TableName("mqtt_exchange")
public class MqttExchangeEntity extends AssignBaseEntity {
    @ApiModelProperty("交换机名称")
    @TableField("EXCHANGE_NAME")
    @NotBlank(message = "交换机名称不能为空")
    private String exchangeName;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value = "所属部门id", readOnly = true)
    @TableField("DEPT_ID")
    private Long deptId;

    @ApiModelProperty("是否启用：1-是、0-否")
    @TableField("STATUS")
    private String status;

    @ApiModelProperty("备注")
    @TableField("REMARK")
    private String remark;
}
