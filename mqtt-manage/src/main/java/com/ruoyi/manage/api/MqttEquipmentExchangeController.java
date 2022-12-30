package com.ruoyi.manage.api;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.manage.domain.param.EquipmentBindingExchangeParam;
import com.ruoyi.manage.service.MqttEquipmentExchangeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: Class
 */
@Api(value = "设备与交换机绑定管理", tags = "设备与交换机绑定管理")
@RestController
@RequestMapping("/binding")
public class MqttEquipmentExchangeController extends BaseController {
    @Resource
    private MqttEquipmentExchangeService mqttEquipmentExchangeService;

    @PostMapping("/list")
    @ApiOperation("进行批量设备与交换机进行绑定")
    @Log(title = "设备绑定管理", businessType = BusinessType.OTHER)
    public AjaxResult equipmentBinding(@Validated @RequestBody EquipmentBindingExchangeParam param) {
        // 判断待绑定的设备编号是否为空
        if (StringUtils.isEmpty(param.getEquipmentNoList())) {
            return null;
        }
        return null;
    }
}
