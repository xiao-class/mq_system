package com.ruoyi.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Class
 * 将所有连接存储到map里面
 */
@Configuration
public class SocketConfig {
    @Bean("socketMap")
    public Map<String, Socket> getSocketMap() {
        return new HashMap<String, Socket>();
    }
}
