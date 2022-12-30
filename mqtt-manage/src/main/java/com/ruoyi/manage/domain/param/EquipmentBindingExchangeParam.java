package com.ruoyi.manage.domain.param;

import com.ruoyi.common.core.param.BaseParam;
import com.ruoyi.manage.domain.entity.MqttEquipmentExchangeEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: Class
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(value = "进行设备与交换机绑定参数实体")
public class EquipmentBindingExchangeParam extends BaseParam<MqttEquipmentExchangeEntity> {
    @ApiModelProperty(value = "待绑定的设备编号")
    private List<String> equipmentNoList;

    @ApiModelProperty(value = "待绑定的交换机id")
    @NotNull(message = "请选择绑定的交换机")
    private Long exchangeId;
}
