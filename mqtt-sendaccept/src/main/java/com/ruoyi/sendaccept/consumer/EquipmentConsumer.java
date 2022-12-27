package com.ruoyi.sendaccept.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Class
 */
@Component
public class EquipmentConsumer {
    private static final Logger log = LoggerFactory.getLogger(EquipmentConsumer.class);

    /**
     * @param message 定义处理消息的方法
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "equipment.test.queue"),
            exchange = @Exchange(name = "equipment_exchange", type = ExchangeTypes.DIRECT),
            key = "test"))
    public static void esdProcess(final Map<String, Object> message) {
        log.info("接收到的map为{}", message);
        final String ip = (String) message.get("ip");
    }
}
