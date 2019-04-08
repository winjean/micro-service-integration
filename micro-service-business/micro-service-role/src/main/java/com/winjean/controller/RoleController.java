package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.feignClient.UserFeignClient;
import com.winjean.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;


@RestController
//@RefreshScope
public class RoleController {

    @Resource
    private UserFeignClient userFeignClient;

    @Autowired
    private RoleService roleService;

    @Value("${winjean}")
    private String configValue;

    @GetMapping("/")
    public Object index() {
        return "role index";
    }

    @PostMapping("add")
    public Object addRole(@RequestBody JSONObject json) {

        roleService.addRole(json);
        userFeignClient.addUser(json);

        json.put("configValue",configValue);

        return json;
    }

    @GetMapping("delete")
    public Object deleteRole(@RequestBody JSONObject json, HttpServletRequest request) {
//        roleService.deleteRole(json);
//        userFeignClient.deleteUser(json);
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()){
            System.out.println(names.nextElement() +"---"+request.getHeader(names.nextElement()));
        }


        return json;
    }

    @PostMapping("update")
    public Object updateRole(@RequestBody JSONObject json) {
        roleService.updateRole(json);
        userFeignClient.updateUser(json);

        return json;
    }

    @PostMapping("query")
    public Object queryRole(@RequestBody JSONObject json) {
        roleService.queryRole(json);
        userFeignClient.queryUser(json);

        return json;
    }

    @PostMapping("queryList")
    public Object queryRoleList(@RequestBody JSONObject json) {
        roleService.queryRoleList(json);
        userFeignClient.queryUserList(json);

        return json;
    }
}