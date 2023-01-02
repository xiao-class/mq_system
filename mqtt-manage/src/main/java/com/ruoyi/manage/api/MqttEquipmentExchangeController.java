package com.ruoyi.manage.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.DataModel;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.manage.domain.entity.MqttEquipmentExchangeEntity;
import com.ruoyi.manage.domain.entity.MqttExchangeEntity;
import com.ruoyi.manage.domain.param.EquipmentBindingExchangeParam;
import com.ruoyi.manage.service.MqttEquipmentExchangeService;
import com.ruoyi.manage.service.MqttExchangeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Class
 */
@Api(value = "设备与交换机绑定管理", tags = "设备与交换机绑定管理")
@RestController
@RequestMapping("/binding")
public class MqttEquipmentExchangeController extends BaseController {
    @Resource
    private MqttEquipmentExchangeService mqttEquipmentExchangeService;
    @Resource
    private MqttExchangeService mqttExchangeService;

    @PostMapping("/list")
    @ApiOperation("进行批量设备与交换机进行绑定")
    @Log(title = "设备绑定管理", businessType = BusinessType.OTHER)
    public AjaxResult equipmentBinding(@Validated @RequestBody EquipmentBindingExchangeParam param) {
        // 判断待绑定的设备编号是否为空
        if (StringUtils.isEmpty(param.getEquipmentNoList())) {
            return null;
        }
        // 创建批量插入集合对象
        List<MqttEquipmentExchangeEntity> equipmentExchangeEntityList = new ArrayList<>();
        // 判断设备是否已经与交换机进行绑定及权限
        for (String s : param.getEquipmentNoList()) {
            mqttEquipmentExchangeService.checkEquipmentNoBinDing(param.getExchangeId(), s, getDeptId());
            equipmentExchangeEntityList.add(new MqttEquipmentExchangeEntity()
                    .setEquipmentNo(s).setExchangeId(param.getExchangeId()));
        }
        // 进行插入
        boolean b = mqttEquipmentExchangeService.saveBatch(equipmentExchangeEntityList);
        return toAjax(b);
    }

    @ApiOperation("获取交换机下拉框")
    @GetMapping("/exchange")
    public DataModel<List<MqttExchangeEntity>> queryExchangeSelect() {
        List<MqttExchangeEntity> list = mqttExchangeService.list(new QueryWrapper<>(new MqttExchangeEntity())
                .select("EXCHANGE_NAME", "ID")
                .eq("DEPT_ID", getDeptId())
                .orderByDesc("CREATE_TIME"));
        return DataModel.success(list);
    }
}
