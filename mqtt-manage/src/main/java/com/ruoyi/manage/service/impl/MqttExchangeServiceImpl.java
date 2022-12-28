package com.ruoyi.manage.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.service.BaseServiceImpl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.manage.domain.entity.MqttExchangeEntity;
import com.ruoyi.manage.mapper.MqttExchangeMapper;
import com.ruoyi.manage.service.MqttExchangeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: Class
 */
@Service
public class MqttExchangeServiceImpl extends BaseServiceImpl<MqttExchangeMapper, MqttExchangeEntity>
        implements MqttExchangeService {

    @Resource
    private MqttExchangeMapper mqttExchangeMapper;

    /**
     * 校验交换机名称是否唯一
     *
     * @param mqttExchangeEntity 交换机信息
     * @return 结果
     */
    @Override
    public String checkExchangeNameUnique(MqttExchangeEntity mqttExchangeEntity) {
        // 获取id，不存在则赋值为-1
        Long id = StringUtils.isNull(mqttExchangeEntity.getId()) ? -1L : mqttExchangeEntity.getId();
        // 根据交换机名称获取信息
        MqttExchangeEntity info = new LambdaQueryChainWrapper<>(mqttExchangeMapper)
                .eq(MqttExchangeEntity::getExchangeName, mqttExchangeEntity.getExchangeName())
                .one();
        // 进行判断
        if (StringUtils.isNotNull(info) && info.getId().longValue() != id.longValue()) {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 判断该交换机是否启用
     *
     * @param id 交换机id
     * @return {@link UserConstants#EXCHANGE_ENABLE}
     */
    @Override
    public String checkExchangeStatus(Long id) {
        // 根据id查找对应信息
        MqttExchangeEntity one = new LambdaQueryChainWrapper<>(mqttExchangeMapper)
                .select(MqttExchangeEntity::getStatus)
                .eq(MqttExchangeEntity::getId, StringUtils.isNull(id) ? -1L : id)
                .one();
        // 判断结果
        if (1 == one.getStatus()) {
            return UserConstants.EXCHANGE_ENABLE;
        }
        return UserConstants.EXCHANGE_NOT_ENABLE;
    }
}
