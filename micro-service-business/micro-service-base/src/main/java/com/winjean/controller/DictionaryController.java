package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.model.entity.EntityDictionary;
import com.winjean.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @PostMapping()
    public BaseResponse insert(@Validated @RequestBody EntityDictionary request){

        return null;
    }

    @DeleteMapping("{id}")
    public BaseResponse delete(@PathVariable int id){
        return null;
    }

    @PutMapping()
    public BaseResponse update(@RequestBody EntityDictionary request){
        return null;
    }

    @GetMapping("{id}")
    public BaseResponse query(@PathVariable int id){
        return null;
    }

    @PostMapping("list")
    public BaseResponse list(@RequestBody JSONObject json){
        return null;
    }
}
