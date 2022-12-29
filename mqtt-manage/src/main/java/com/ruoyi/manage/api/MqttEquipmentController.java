package com.ruoyi.manage.api;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.manage.domain.entity.MqttEquipmentEntity;
import com.ruoyi.manage.domain.param.QueryEquipmentListParam;
import com.ruoyi.manage.service.MqttEquipmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Class
 */
@Api(value = "设备管理", tags = "设备管理")
@RestController
@RequestMapping("/equipment")
public class MqttEquipmentController extends BaseController {
    @Resource
    private MqttEquipmentService mqttEquipmentService;

    @ApiOperation("获取设备列表")
    @PostMapping("/list")
    public AjaxResult queryEquipmentList(@RequestBody(required = false) QueryEquipmentListParam param) {
        startPage();
        List<MqttEquipmentEntity> list = mqttEquipmentService.list(param.wrapper()
                .select("ID", "EQUIPMENT_NO", "EQUIPMENT_TYPE", "STATUS", "ONLINE_STATUS")
                .eq("DEPT_ID", getDeptId())
                .orderByDesc("CREATE_TIME"));
        return success(PageInfo.of(list));
    }

    @ApiOperation("")
    @PostMapping("/add")
    @Log(title = "设备管理", businessType = BusinessType.INSERT)
    public AjaxResult addEquipment(@Validated @RequestBody MqttEquipmentEntity mqttEquipmentEntity) {
        // 判断设备编号是否存在
        mqttEquipmentService.checkEquipmentNoUnique(mqttEquipmentEntity.getId(), mqttEquipmentEntity.getEquipmentNo());
        // 将部门写入
        mqttEquipmentEntity.setDeptId(getDeptId());
        // 进行写入
        boolean save = mqttEquipmentService.save(mqttEquipmentEntity);
        return toAjax(save);
    }

    @ApiOperation("进行修改设备信息")
    @PostMapping("/edit")
    @Log(title = "设备管理", businessType = BusinessType.UPDATE)
    public AjaxResult updateEquipment(@Validated @RequestBody MqttEquipmentEntity mqttEquipmentEntity) {
        // 判断是否存在id
        if (StringUtils.isNull(mqttEquipmentEntity.getId())) {
            return null;
        }
        // 判断该设备是否启用
        mqttEquipmentService.checkEquipmentStatus(mqttEquipmentEntity.getId());
        // 判断设备编号是否存在
        mqttEquipmentService.checkEquipmentNoUnique(mqttEquipmentEntity.getId(), mqttEquipmentEntity.getEquipmentNo());
        // 进行修改
        boolean b = mqttEquipmentService.updateById(mqttEquipmentEntity);
        return toAjax(b);
    }
}
