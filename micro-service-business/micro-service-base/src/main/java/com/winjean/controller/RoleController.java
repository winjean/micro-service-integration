package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.model.entity.EntityRole;
import com.winjean.model.entity.EntityUser;
import com.winjean.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public BaseResponse save(@Validated @RequestBody EntityRole role){
        return BaseResponse.getSuccessResponse(roleService.save(role));
    }

    @DeleteMapping("{id}")
    public BaseResponse delete(@PathVariable long id){
        roleService.delete(id);
        return BaseResponse.getSuccessResponse();
    }

    @PutMapping
    public BaseResponse update(@Validated(EntityUser.Update.class) @RequestBody EntityRole role){
        return BaseResponse.getSuccessResponse(roleService.update(role));
    }

    @GetMapping("{id}")
    public BaseResponse query(@PathVariable long id){
        return BaseResponse.getSuccessResponse(roleService.query(id));
    }

    @PostMapping("list")
    public BaseResponse list(@RequestBody JSONObject json){
        Page<EntityRole> page = roleService.list(json);
        return BaseResponse.getSuccessResponse(page);
    }
}
