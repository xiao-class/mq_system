package com.ruoyi.manage.api;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.manage.service.MqttExchangeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    @GetMapping("/list")
    public AjaxResult queryExchangeList() {

    }
}
