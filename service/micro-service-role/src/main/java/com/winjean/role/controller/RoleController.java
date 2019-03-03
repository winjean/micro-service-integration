package com.winjean.role.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.role.feignClient.UserFeignClient;
import com.winjean.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RoleController {

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    private RoleService roleService;

    @PostMapping("add")
    public Object addRole(@RequestBody JSONObject json) {

        roleService.addRole(json);
        userFeignClient.addUser(json);

        return json;
    }

    @PostMapping("delete")
    public Object deleteRole(@RequestBody JSONObject json) {
        roleService.deleteRole(json);
        userFeignClient.deleteUser(json);

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