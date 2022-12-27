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
    private String exchangeName;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @ApiModelProperty("资源目录id")
    @TableField("DEPT_ID")
    private Long deptId;

    @ApiModelProperty("是否停用 0否1是")
    @TableField("STATE")
    private String state;

    @ApiModelProperty("备注")
    @TableField("REMARK")
    private String remark;
}
