package com.winjean.sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class SampleController {

    @Autowired
    private static StringEncryptor encryptor;

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
