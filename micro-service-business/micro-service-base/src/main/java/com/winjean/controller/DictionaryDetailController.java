package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.model.entity.EntityDictionaryDetail;
import com.winjean.model.entity.EntityUser;
import com.winjean.service.DictionaryDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dictionary/detail")
@Slf4j
public class DictionaryDetailController {

    @Autowired
    private DictionaryDetailService dictionaryDetailService;

    @PostMapping
    public BaseResponse save(@Validated @RequestBody EntityDictionaryDetail dictionaryDetail){
        return BaseResponse.getSuccessResponse(dictionaryDetailService.save(dictionaryDetail));
    }

    @DeleteMapping("{id}")
    public BaseResponse delete(@PathVariable long id){
        dictionaryDetailService.delete(id);
        return BaseResponse.getSuccessResponse();
    }

    @PutMapping
    public BaseResponse update(@Validated(EntityUser.Update.class) @RequestBody EntityDictionaryDetail dictionaryDetail){
        return BaseResponse.getSuccessResponse(dictionaryDetailService.update(dictionaryDetail));
    }

    @GetMapping("{id}")
    public BaseResponse query(@PathVariable long id){
        return BaseResponse.getSuccessResponse(dictionaryDetailService.query(id));
    }

    @PostMapping("list")
    public BaseResponse list(@RequestBody JSONObject json){
        Page<EntityDictionaryDetail> page = dictionaryDetailService.list(json);
        return BaseResponse.getSuccessResponse(page);
    }
}
