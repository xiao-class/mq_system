package com.ruoyi.manage.api;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.DataModel;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.manage.domain.entity.MqttExchangeEntity;
import com.ruoyi.manage.domain.param.QueryExchangeListParam;
import com.ruoyi.manage.service.MqttExchangeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: Class
 */
@Api(value = "交换机管理", tags = "交换机管理")
@RestController
@RequestMapping("/exchange")
public class MqttExchangeController extends BaseController {
    @Resource
    private MqttExchangeService mqttExchangeService;

    @ApiOperation("获取交换机数据列表")
    @PostMapping("/list")
    public DataModel<PageInfo<MqttExchangeEntity>> queryExchangeList(@RequestBody(required = false) QueryExchangeListParam param) {
        startPage();
        List<MqttExchangeEntity> list = mqttExchangeService.list(param.wrapper()
                .select("ID", "EXCHANGE_NAME", "STATUS", "CREATE_TIME", "CREATE_USER_NAME")
                .eq("DEPT_ID", getDeptId())
                .orderByDesc("CREATE_TIME"));
        return DataModel.success(PageInfo.of(list));
    }

    @ApiOperation("添加交换机信息")
    @PostMapping("/add")
    @Log(title = "交换机管理", businessType = BusinessType.INSERT)
    public AjaxResult addExchange(@Validated @RequestBody MqttExchangeEntity mqttExchangeEntity) {
        // 判断交换机信息是否存在
        mqttExchangeService.checkExchangeNameUnique(mqttExchangeEntity.getId(), mqttExchangeEntity.getExchangeName());
        // 写入部门id
        mqttExchangeEntity.setDeptId(getDeptId());
        logger.info("添加交换机信息:" + mqttExchangeEntity);
        // 进行添加
        boolean save = mqttExchangeService.save(mqttExchangeEntity);
        return toAjax(save);
    }

    @ApiOperation("修改交换机信息")
    @PostMapping("/edit")
    @Log(title = "交换机管理", businessType = BusinessType.UPDATE)
    public AjaxResult updateExchange(@Validated @RequestBody MqttExchangeEntity mqttExchangeEntity) {
        // 判断id是否携带
        if (StringUtils.isNull(mqttExchangeEntity.getId())) {
            return null;
        }
        // 判断该交换机名称是否已经被占用
        mqttExchangeService.checkExchangeNameUnique(mqttExchangeEntity.getId(), mqttExchangeEntity.getExchangeName());
        // 进行修改
        boolean b = mqttExchangeService.updateById(mqttExchangeEntity);
        return toAjax(b);
    }

    @ApiOperation("根据id删除交换机信息")
    @DeleteMapping("/delete/{id}")
    @Log(title = "交换机管理", businessType = BusinessType.DELETE)
    public AjaxResult deleteExchange(@PathVariable Long id) {
        // 判断id是否存在
        if (StringUtils.isNull(id)) {
            return null;
        }
        // 判断该交换机是否被启用
        mqttExchangeService.checkExchangeStatus(id);
        // 进行删除
        boolean b = mqttExchangeService.removeById(id);
        return toAjax(b);
    }

    @ApiOperation("根据id获取详细交换机信息")
    @GetMapping("/get/{id}")
    public DataModel<MqttExchangeEntity> getExchangeById(@PathVariable Long id) {
        // 判断id是否存在
        if (StringUtils.isNull(id)) {
            return null;
        }
        return DataModel.success(mqttExchangeService.getById(id));
    }

    @ApiOperation("修改交换机的状态")
    @PostMapping("/edit/status")
    @Log(title = "交换机管理", businessType = BusinessType.UPDATE)
    public AjaxResult editExchangeStatus(Long id, String status) {
        // 判断修改的id及状态是否存在
        if (StringUtils.isNull(id)) {
            return null;
        }
        if (StringUtils.isEmpty(status)) {
            return null;
        }
        MqttExchangeEntity mqttExchangeEntity = (MqttExchangeEntity) new MqttExchangeEntity().setStatus(status).setId(id);
        return toAjax(mqttExchangeService.updateById(mqttExchangeEntity));
    }
}
