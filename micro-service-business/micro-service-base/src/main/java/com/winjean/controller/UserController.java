package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.model.entity.EntityUser;
import com.winjean.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController  {

    @Autowired
    private UserService userService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_SELECT')")
    public BaseResponse save(@Validated @RequestBody EntityUser user){
        return BaseResponse.getSuccessResponse(userService.save(user));
    }

    @DeleteMapping("{id}")
    public BaseResponse delete(@PathVariable long id){
        userService.delete(id);
        return BaseResponse.getSuccessResponse();
    }

    @PutMapping
    public BaseResponse update(@Validated(EntityUser.Update.class) @RequestBody EntityUser user){
        return BaseResponse.getSuccessResponse(userService.update(user));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','USER_ALL','USER_SELECT')")
    public BaseResponse query(@PathVariable long id){
        return BaseResponse.getSuccessResponse(userService.query(id));
    }

    @PostMapping("list")
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_SELECT')")
    public BaseResponse list(@RequestBody JSONObject json){
        Page<EntityUser> page = userService.list(json);
        return BaseResponse.getSuccessResponse(page);
    }
}
