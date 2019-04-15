package com.winjean.sample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author ：winjean
 * @date ：Created in 2019/4/15 10:51
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //设置浏览器接收的服务前缀,同时也是后台服务推送的前缀
        config.enableSimpleBroker("/topic");
        //设置浏览器发送消息的服务前缀,也就是后台服务接收前台信息的前缀
        config.setApplicationDestinationPrefixes("/app");
    }

    //添加服务端点 浏览器链接这个地址
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/my-websocket")
                .setAllowedOrigins("*")
                .withSockJS();
    }

}
