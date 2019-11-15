package com.hupa.exp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 开启WebSocket支持
 * @author zhengkai
 */
@Configuration
public class WebSocketConfig {
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}