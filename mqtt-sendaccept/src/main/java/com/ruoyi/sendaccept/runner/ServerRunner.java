package com.ruoyi.sendaccept.runner;

import com.ruoyi.framework.config.SocketConfig;
import com.ruoyi.sendaccept.sender.EquipmentSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Class
 */
@Component
public class ServerRunner implements CommandLineRunner {
    @Autowired
    private Environment environment;
    @Autowired
    private SocketConfig socketConfig;
    @Autowired
    private EquipmentSender equipmentSender;

    private static final Logger log = LoggerFactory.getLogger(ServerRunner.class);

    @Override
    public void run(final String... args) throws Exception {
        //线程池
        final ExecutorService newCacheThreadPool = Executors.newCachedThreadPool();
        final ServerSocket server = new ServerSocket(this.environment.getProperty("server.mqserver.port", Integer.class));
        log.info("mq端口启动项服务启动...");
        final Map<String, Socket> socketMap = socketConfig.getSocketMap();
        while (true) {
            final Socket socket = server.accept();
            final String ip = socket.getInetAddress().toString().substring(1);
            final String port = Integer.toString(socket.getPort());
            socketMap.put(ip + ":" + port, socket);
            log.info("放进了容器中: {}", socket);
            newCacheThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        handler(socket);
                    } catch (final Exception e) {
                        log.error("端口：{}断开", port);
                    }
                }
            });
            newCacheThreadPool.execute(() -> {
                // 每隔5秒发送数据
                try {
                    while (true) {
                        socket.sendUrgentData(0xF);
                        Thread.sleep(5 * 1000);
                    }
                } catch (final InterruptedException | IOException e) {
                    log.error("端口：{}断开", port);
                } finally {
                    try {
                        socketMap.remove(ip + ":" + port);
                        socket.close();
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void handler(final Socket socket) throws Exception {
        byte[] bytes = null;
        byte[] data = null;
        try {
            final InputStream inputStream = socket.getInputStream();
            final String ip = socket.getInetAddress().toString().substring(1);
            final String port = Integer.toString(socket.getPort());
            while (true) {
                bytes = new byte[1024];
                //读取数据(阻塞)
                final int read = inputStream.read(bytes);
                // 获取真实读取字节数
                if (read != -1) {
                    data = new byte[read];
                    System.arraycopy(bytes, 0, data, 0, read);
                    log.info("字节:{}", data);
                }
                if (read != -1) {
                    log.info("============================连接设备发来数据=======================================");
                    data = new byte[read];
                    // 进行复制
                    System.arraycopy(bytes, 0, data, 0, read);
                    log.info("字节:{}", data);
                    final String s = new String(data, StandardCharsets.UTF_8);
                    log.info("=======接收的数据{}", s);
                    // 放入消息中间件
                    final Map<String, Object> map = new HashMap<>();
                    map.put("data", s);
                    map.put("ip", ip);
                    map.put("port", port);
                    //  使用rabbitMq发送数据
                    equipmentSender.equipmentSender(map);
                    log.info("======================================================================================");
                } else {
                    break;
                }
            }
        } catch (final IOException e) {
            log.info("socket关闭");
        } finally {
            try {
                final Map<String, Socket> socketMap = socketConfig.getSocketMap();
                final String ip = socket.getInetAddress().toString().substring(1);
                final String port = Integer.toString(socket.getPort());
                socketMap.remove(ip + ":" + port);
                socket.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
    }
}
