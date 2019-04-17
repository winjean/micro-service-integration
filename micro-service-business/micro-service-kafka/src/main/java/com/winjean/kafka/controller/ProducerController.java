package com.winjean.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author ：winjean
 * @date ：Created in 2019/4/13 14:55
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@RestController
@Slf4j
public class ProducerController {

    @Resource
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("/")
    @ResponseBody
    public String test() {
        return "index";
    }

    @GetMapping("send")
    public String send(@RequestParam String msg){
        kafkaTemplate.send("topic-1", msg);
        return "success";
    }
}
