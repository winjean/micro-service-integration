package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.model.entity.EntityUser;
import com.winjean.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController  {

    @Autowired
    private UserService userService;

    @PostMapping
    public BaseResponse insert(@Validated @RequestBody EntityUser user){
        userService.insert(user);
        return BaseResponse.getSuccessResponse();
    }

    @DeleteMapping("{id}")
    public BaseResponse delete(@PathVariable long id){
        userService.delete(id);
        return BaseResponse.getSuccessResponse();
    }

    @PutMapping("update")
    public BaseResponse update(@Validated(EntityUser.Update.class) @RequestBody EntityUser user){
        userService.update(user);
        return BaseResponse.getSuccessResponse();
    }

    @GetMapping("{id}")
    public BaseResponse query(@PathVariable long id){
        userService.query(id);
        return null;
    }

    @PostMapping("list")
    public BaseResponse list(@RequestBody JSONObject json){
        Page<EntityUser> page = userService.list(json);
        return BaseResponse.getSuccessResponse(page);
    }
}
