package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.model.entity.EntityDictionary;
import com.winjean.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dictionary")
@Slf4j
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @PostMapping
    public BaseResponse save(@Validated @RequestBody EntityDictionary dictionary){
        return BaseResponse.getSuccessResponse(dictionaryService.save(dictionary));
    }

    @DeleteMapping("{id}")
    public BaseResponse delete(@PathVariable long id){
        dictionaryService.delete(id);
        return BaseResponse.getSuccessResponse();
    }

    @PutMapping
    public BaseResponse update(@Validated(EntityDictionary.Update.class) @RequestBody EntityDictionary dictionary){
        return BaseResponse.getSuccessResponse(dictionaryService.update(dictionary));
    }

    @GetMapping("{id}")
    public BaseResponse query(@PathVariable long id){
        return BaseResponse.getSuccessResponse(dictionaryService.query(id));
    }

    @PostMapping("list")
    public BaseResponse list(@RequestBody JSONObject json){
        Page<EntityDictionary> page = dictionaryService.list(json);
        return BaseResponse.getSuccessResponse(page);
    }
}
