package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * @author winjean
 */

@RestController
public class PermissionController {

    @Autowired
    private PermissionService userService;

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
