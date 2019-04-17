package com.winjean.test.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Random;

@RestController
@Slf4j
public class TestController {

    @GetMapping("/")
    @ResponseBody
    public String test() {
        JSONObject json = new JSONObject();
        json.put("datetime", new Date());
        json.put("Random1", new Random().nextInt(10));
        json.put("Random2", new Random().nextInt(10));
        json.put("Random3", new Random().nextInt(10));
        json.put("level","trace");
        log.trace(json.toJSONString());

        json.put("Random1", new Random().nextInt(10));
        json.put("Random2", new Random().nextInt(10));
        json.put("Random3", new Random().nextInt(10));
        json.put("level","debug");
        log.debug(json.toJSONString());

        json.put("Random1", new Random().nextInt(10));
        json.put("Random2", new Random().nextInt(10));
        json.put("Random3", new Random().nextInt(10));
        json.put("level","info");
        log.info(json.toJSONString());

        json.put("Random1", new Random().nextInt(10));
        json.put("Random2", new Random().nextInt(10));
        json.put("Random3", new Random().nextInt(10));
        json.put("level","warn");
        log.warn(json.toJSONString());

        json.put("Random1", new Random().nextInt(10));
        json.put("Random2", new Random().nextInt(10));
        json.put("Random3", new Random().nextInt(10));
        json.put("level","error");
        log.error(json.toJSONString());

        return "-------------test index-------------";
    }
}
