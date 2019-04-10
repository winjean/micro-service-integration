package com.winjean.sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class TestController {

    @GetMapping("/")
    @ResponseBody
    public String test() {
        log.error("this is error test !!");
        log.debug("this is debug test !!");
        log.info("this is info test !!");
        return "test index";
    }
}
