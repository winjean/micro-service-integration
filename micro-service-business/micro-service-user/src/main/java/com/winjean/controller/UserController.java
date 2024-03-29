package com.winjean.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.model.request.RequestUserInsert;
import com.winjean.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author winjean
 */

@RestController
@Slf4j
public class UserController  {

    @Autowired
    private UserService userService;

    @GetMapping(value = {"/","/index"})
    @ResponseBody
    public String test() {
        return "user service index";
    }


    @GetMapping("/rateLimit")
    @SentinelResource(value = "resource",blockHandler = "handler")
    public String resource(){
        return "sssss";
    }
    public String handler(BlockException blockException){
        return  "handle-"+blockException.getClass().getName();
    }

    @PostMapping("insert")
    public Object insert(@Validated @RequestBody RequestUserInsert request){

        BaseResponse response = BaseResponse.getSuccessResponse();
        try {
            userService.insert(request);
        }catch (Exception e){
            response = BaseResponse.getFailureResponse(e.getMessage());
            log.error(e.getMessage(),e);
        }

        return response;
    }

    @GetMapping("delete")
    public Object delete(@RequestParam Map map){
        userService.delete(map);
        return map;
    }

    @PostMapping("update")
    public Object update(@RequestBody JSONObject json){
        userService.update(json);
        return json;
    }

    @PostMapping("query")
    public Object query(@RequestBody JSONObject json){
        userService.query(json);
        return json;
    }

    @PostMapping("queryList")
    public Object queryList(@RequestBody JSONObject json){
        userService.queryList(json);
        return json;
    }
}
