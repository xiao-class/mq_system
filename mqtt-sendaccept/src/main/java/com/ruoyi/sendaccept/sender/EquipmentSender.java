package com.ruoyi.sendaccept.sender;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Author: Class
 */
@Component
public class EquipmentSender {
    private static final Logger log = LoggerFactory.getLogger(EquipmentSender.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void equipmentSender(final Map<String, Object> massage) {
        final String exchangeName = "equipment_exchange";
        final String key = "test";
        log.info("向rabbitMQ推送测试消息{}", massage);
        rabbitTemplate.convertAndSend(exchangeName, key, massage);
    }
}
