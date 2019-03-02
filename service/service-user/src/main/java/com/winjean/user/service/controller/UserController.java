package com.winjean.user.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类描述：
 * 创建人：winjean
 * 创建时间：2018/9/21 14:57
 *
 * @version V 1.0
 */
@RestController
@RequestMapping("/user-service-api")
public class UserController  {

//    @Override
//    public User getUser(@RequestParam("id") int id) {
//        return null;
//    }
//    @Value("${server.port}")
    private String serverPort;

//    @Value("${winjean}")
    private String winjean;

    /*@Override
    @GetMapping(value = "/getUserNameById")
    public String getUserName() {
        return  serverPort+ ":" + winjean;
    }*/
}
