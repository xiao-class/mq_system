package com.ruoyi.manage.domain.param;

import com.ruoyi.common.annotation.Condition;
import com.ruoyi.common.core.param.BaseParam;
import com.ruoyi.enums.core.Operator;
import com.ruoyi.manage.domain.entity.MqttExchangeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: Class
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "交换机列表查询条件")
public class QueryExchangeListParam extends BaseParam<MqttExchangeEntity> {
    @ApiModelProperty("企业名称")
    @Condition(field = "EXCHANGE_NAME", type = Operator.LIKE)
    private String exchangeName;

    @ApiModelProperty(value = "是否启用：1-是、0-否")
    @Condition(field = "STATUS", type = Operator.EQ)
    private Integer status;
}
