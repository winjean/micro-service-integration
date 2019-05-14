package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.model.request.RequestUserInsert;
import com.winjean.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
public class UserController  {

    @Autowired
    private UserService userService;

    @PostMapping("insert")
    public BaseResponse insert(@Validated @RequestBody RequestUserInsert request){

        BaseResponse response = BaseResponse.getSuccessResponse();
        try {
            userService.insert(request);
        }catch (Exception e){
            response = BaseResponse.getFailureResponse(e.getMessage());
            log.error(e.getMessage(),e);
        }

        return response;
    }

    @DeleteMapping("delete")
    public BaseResponse delete(@RequestParam Map map){
        userService.delete(map);
        return null;
    }

    @PutMapping("update")
    public BaseResponse update(@RequestBody JSONObject json){
        userService.update(json);
        return null;
    }

    @GetMapping("query")
    public BaseResponse query(@RequestBody JSONObject json){
        userService.query(json);
        return null;
    }

    @PostMapping("queryList")
    public BaseResponse queryList(@RequestBody JSONObject json){
        userService.queryList(json);
        return null;
    }
}
