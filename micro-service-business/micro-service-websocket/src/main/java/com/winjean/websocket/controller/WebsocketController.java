package com.winjean.websocket.controller;

import com.winjean.websocket.model.SocketMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author ：winjean
 * @date ：Created in 2019/4/13 14:55
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@Controller
@Slf4j
public class WebsocketController {

    /**
     * websochet消息发送对象
     */
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    /**
     * 跳转到测试页面
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "/index";
    }

    //测试页面显示后台消息推送次数
    private int count=0;

    //接收浏览器消息路径设置
    @MessageMapping("/send")
    //服务端向浏览器推送地址设置
    @SendTo("/topic/send")
    public SocketMessage send(SocketMessage message) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.setData("浏览器消息: "+ message.getMessage()+ "---"+ df.format(new Date()));
        return message;
    }

    //接收浏览器消息路径设置
    @MessageMapping("/send-single")
    //服务端向浏览器推送地址设置
    @SendToUser("/topic/send-single")
    public SocketMessage sendSingle(SocketMessage message) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        message.setData("浏览器消息: "+ message.getMessage()+ "---"+ df.format(new Date()));
        return message;
    }

    //由后台发送到浏览器服务
    @SendTo("/topic/callback")
    //定时5秒给页面推一次数据
    @Scheduled(cron="0/5 * * * * ?")
    public void callback() throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("推送消息了"+df.format(new Date()));
        //向页面这个地址推送消息
        SocketMessage sm = new SocketMessage();
        sm.setData(count+ "---" +df.format(new Date()));
        messagingTemplate.convertAndSend("/topic/callback",sm );
        count++;
    }

}
