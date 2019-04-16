package com.winjean.sample.interceptor;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

/**
 * @author ：winjean
 * @date ：Created in 2019/4/16 14:08
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
@Component
public class MyChannelInterceptor implements ChannelInterceptor {

//    @Autowired
//    private SimpMessagingTemplate messagingTemplate;

    @Override
    public boolean preReceive(MessageChannel channel) {
        System.out.println(this.getClass().getCanonicalName() + " preReceive");
        return true;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        System.out.println(this.getClass().getCanonicalName() + " preSend");
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        //检测用户订阅内容（防止用户订阅不合法频道）
        if (StompCommand.SUBSCRIBE.equals(command)) {
            System.out.println(this.getClass().getCanonicalName() + " 用户订阅目的地=" + accessor.getDestination());
            // 如果该用户订阅的频道不合法直接返回null前端用户就接受不到该频道信息
        }
        return message;

    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        System.out.println(this.getClass().getCanonicalName() +" afterSendCompletion");
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();
        if (StompCommand.SUBSCRIBE.equals(command)){
            System.out.println(this.getClass().getCanonicalName() + " 订阅消息发送成功");
//            messagingTemplate.convertAndSend("/topic/callback","消息发送成功");
        }
        //如果用户断开连接
        if (StompCommand.DISCONNECT.equals(command)){
            System.out.println(this.getClass().getCanonicalName() + "用户断开连接成功");
//            messagingTemplate.convertAndSend("/topic/callback","{'msg':'用户断开连接成功'}");
        }
    }
}
