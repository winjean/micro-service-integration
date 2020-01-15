package com.winjean.foundation.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.foundation.domain.Dictionary;
import com.winjean.foundation.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dictionary")
@Slf4j
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','DICTIONARY_ALL','DICTIONARY_CREATE')")
    public BaseResponse save(@Validated @RequestBody Dictionary dictionary){
        return BaseResponse.getSuccessResponse(dictionaryService.save(dictionary));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DICTIONARY_ALL','DICTIONARY_DELETE')")
    public BaseResponse delete(@PathVariable long id){
        dictionaryService.delete(id);
        return BaseResponse.getSuccessResponse();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN','DICTIONARY_ALL','DICTIONARY_UPDATE')")
    public BaseResponse update(@Validated(Dictionary.Update.class) @RequestBody Dictionary dictionary){
        return BaseResponse.getSuccessResponse(dictionaryService.update(dictionary));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DICTIONARY_ALL','DICTIONARY_SELECT')")
    public BaseResponse query(@PathVariable long id){
        return BaseResponse.getSuccessResponse(dictionaryService.query(id));
    }

    @PostMapping("list")
    @PreAuthorize("hasAnyRole('ADMIN','DICTIONARY_ALL','DICTIONARY_SELECT')")
    public BaseResponse list(@RequestBody JSONObject json){
        Page<Dictionary> page = dictionaryService.list(json);
        return BaseResponse.getSuccessResponse(page);
    }
}
