package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.model.entity.DictionaryDetail;
import com.winjean.model.entity.User;
import com.winjean.service.DictionaryDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dictionary/detail")
@Slf4j
public class DictionaryDetailController {

    @Autowired
    private DictionaryDetailService dictionaryDetailService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','DICTIONARY_ALL','DICTIONARY_CREATE')")
    public BaseResponse save(@Validated @RequestBody DictionaryDetail dictionaryDetail){
        return BaseResponse.getSuccessResponse(dictionaryDetailService.save(dictionaryDetail));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DICTIONARY_ALL','DICTIONARY_DELETE')")
    public BaseResponse delete(@PathVariable long id){
        dictionaryDetailService.delete(id);
        return BaseResponse.getSuccessResponse();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN','DICTIONARY_ALL','DICTIONARY_UPDATE')")
    public BaseResponse update(@Validated(User.Update.class) @RequestBody DictionaryDetail dictionaryDetail){
        return BaseResponse.getSuccessResponse(dictionaryDetailService.update(dictionaryDetail));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DICTIONARY_ALL','DICTIONARY_SELECT')")
    public BaseResponse query(@PathVariable long id){
        return BaseResponse.getSuccessResponse(dictionaryDetailService.query(id));
    }

    @PostMapping("list")
    @PreAuthorize("hasAnyRole('ADMIN','DICTIONARY_ALL','DICTIONARY_SELECT')")
    public BaseResponse list(@RequestBody JSONObject json){
        Page<DictionaryDetail> page = dictionaryDetailService.list(json);
        return BaseResponse.getSuccessResponse(page);
    }
}
