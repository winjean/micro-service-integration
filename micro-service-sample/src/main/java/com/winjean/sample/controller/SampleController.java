package com.winjean.sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class SampleController {

    @GetMapping("/")
    @ResponseBody
    public String test() {
        return "index";
    }
}
