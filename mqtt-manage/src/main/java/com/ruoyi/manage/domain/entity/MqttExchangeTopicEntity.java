package com.ruoyi.manage.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.core.domain.AssignBaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Author: Class
 */
@EqualsAndHashCode(callSuper = true)
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "topic", description = "topic信息实体")
@TableName("MQTT_EXCHANGE_TOPIC")
public class MqttExchangeTopicEntity extends AssignBaseEntity {
    @ApiModelProperty("topic名称")
    @NotBlank(message = "请输入topic名称")
    private String topicName;

    @ApiModelProperty(value = "交换机id")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @NotNull(message = "请选择交换机进行绑定")
    private Long exchangeId;

    @ApiModelProperty("是否启用 关联字典表：mqtt_topic_status")
    private String status;
}
