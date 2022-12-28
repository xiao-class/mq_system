package com.ruoyi.manage.api;

import com.github.pagehelper.PageInfo;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.manage.domain.entity.MqttExchangeEntity;
import com.ruoyi.manage.domain.param.QueryExchangeListParam;
import com.ruoyi.manage.service.MqttExchangeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public AjaxResult queryExchangeList(@RequestBody(required = false) QueryExchangeListParam param) {
        startPage();
        List<MqttExchangeEntity> list = mqttExchangeService.list(param.wrapper().eq("DEPT_ID", getDeptId()).orderByDesc("CREATE_TIME"));
        return AjaxResult.success(PageInfo.of(list));
    }
}
