package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.model.entity.EntityPermission;
import com.winjean.model.entity.EntityUser;
import com.winjean.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Slf4j
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping
    public BaseResponse save(@Validated @RequestBody EntityPermission permission){
        return BaseResponse.getSuccessResponse(permissionService.save(permission));
    }

    @DeleteMapping("{id}")
    public BaseResponse delete(@PathVariable long id){
        permissionService.delete(id);
        return BaseResponse.getSuccessResponse();
    }

    @PutMapping
    public BaseResponse update(@Validated(EntityUser.Update.class) @RequestBody EntityPermission permission){
        return BaseResponse.getSuccessResponse(permissionService.update(permission));
    }

    @GetMapping("{id}")
    public BaseResponse query(@PathVariable long id){
        return BaseResponse.getSuccessResponse(permissionService.query(id));
    }

    @PostMapping("list")
    public BaseResponse list(@RequestBody JSONObject json){
        Page<EntityPermission> page = permissionService.list(json);
        return BaseResponse.getSuccessResponse(page);
    }
}
