package com.winjean.foundation.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.foundation.domain.User;
import com.winjean.foundation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_CREATE')")
    public BaseResponse save(@Validated @RequestBody User user){
        return BaseResponse.getSuccessResponse(userService.save(user));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_DELETE')")
    public BaseResponse delete(@PathVariable long id){
        userService.delete(id);
        return BaseResponse.getSuccessResponse();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_UPDATE')")
    public BaseResponse update(@Validated(User.Update.class) @RequestBody User user){
        return BaseResponse.getSuccessResponse(userService.update(user));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_SELECT')")
    public BaseResponse query(@PathVariable long id){
        return BaseResponse.getSuccessResponse(userService.query(id));
    }

    @PostMapping("list")
    @PreAuthorize("hasAnyRole('ADMIN','USER_ALL','USER_SELECT')")
    public BaseResponse list(@RequestBody JSONObject json, Pageable pageable){
        Page<User> page = userService.list(json, pageable);
        return BaseResponse.getSuccessResponse(page);
    }
}
