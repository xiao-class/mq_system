package com.ruoyi.manage.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.google.common.annotations.VisibleForTesting;
import com.ruoyi.common.constant.ExchangeConstants;
import com.ruoyi.common.core.service.BaseServiceImpl;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.manage.domain.entity.MqttExchangeEntity;
import com.ruoyi.manage.mapper.MqttExchangeMapper;
import com.ruoyi.manage.service.MqttExchangeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.ruoyi.common.constant.ErrorCodeConstants.EXCHANGE_NAME_ENABLE;
import static com.ruoyi.common.constant.ErrorCodeConstants.EXCHANGE_NAME_EXISTS;

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
     * @param id           交换机id
     * @param exchangeName 交换机名称
     * @throws ServiceException 交换机名称已存在
     */
    @Override
    @VisibleForTesting
    public void checkExchangeNameUnique(Long id, String exchangeName) {
        if (StringUtils.isEmpty(exchangeName)) {
            return;
        }
        // 根据交换机名称获取信息
        MqttExchangeEntity info = new LambdaQueryChainWrapper<>(mqttExchangeMapper)
                .eq(MqttExchangeEntity::getExchangeName, exchangeName)
                .one();
        if (StringUtils.isNull(info)) {
            return;
        }
        // 如果不存在id，则无需比较是否为同一id
        if (StringUtils.isNull(id)) {
            throw exception(EXCHANGE_NAME_EXISTS, exchangeName);
        }
        // 比较id是否一样
        if (!info.getId().equals(id)) {
            throw exception(EXCHANGE_NAME_EXISTS, exchangeName);
        }
    }

    /**
     * 判断该交换机是否启用
     *
     * @param id 交换机id
     * @throws ServiceException 交换机已启用
     */
    @Override
    @VisibleForTesting
    public void checkExchangeStatus(Long id) {
        if (StringUtils.isNull(id)) {
            return;
        }
        // 根据id查找对应信息
        MqttExchangeEntity one = new LambdaQueryChainWrapper<>(mqttExchangeMapper)
                .select(MqttExchangeEntity::getStatus)
                .eq(MqttExchangeEntity::getId, id)
                .one();
        if (StringUtils.isNull(one)) {
            return;
        }
        // 判断结果
        if (ExchangeConstants.EXCHANGE_EQUIPMENT_ENABLE.equals(one.getStatus())) {
            throw exception(EXCHANGE_NAME_ENABLE);
        }
    }
}
