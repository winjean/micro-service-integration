package com.winjean.user.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController  {

    @Autowired
    private UserService userService;

    @PostMapping("add")
    public Object addUser(@RequestBody JSONObject json){
        userService.addUser(json);
        return json;
    }

    @GetMapping("delete")
    public Object deleteUser(@RequestParam Map map){
        userService.deleteUser(map);
        return map;
    }

    @PostMapping("update")
    public Object updateUser(@RequestBody JSONObject json){
        userService.updateUser(json);
        return json;
    }

    @PostMapping("query")
    public Object queryUser(@RequestBody JSONObject json){
        userService.queryUser(json);
        return json;
    }

    @PostMapping("queryList")
    public Object queryUserList(@RequestBody JSONObject json){
        userService.queryUserList(json);
        return json;
    }
}
