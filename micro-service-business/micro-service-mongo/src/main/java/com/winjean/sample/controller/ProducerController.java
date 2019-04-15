package com.winjean.sample.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


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


    @GetMapping("/")
    @ResponseBody
    public String test() {
        return "index";
    }

}
