package com.winjean.sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class SampleController {

    @Value("${winjean}")
    private String winjean;

    @GetMapping("/")
    @ResponseBody
    public String test() {
        log.error(winjean + "-sample index");
        log.info(winjean + "-sample index");
        return winjean + "-sample index";
    }
}
