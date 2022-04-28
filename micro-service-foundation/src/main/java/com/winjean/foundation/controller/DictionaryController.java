package com.winjean.foundation.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.foundation.domain.Dictionary;
import com.winjean.foundation.service.DictionaryService;
import com.winjean.logging.annotation.RecordLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("dictionary")
@Slf4j
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    @RecordLog("新增字典信息")
    @PostMapping
//    @PreAuthorize("hasAnyRole('ADMIN','DICTIONARY_ALL','DICTIONARY_CREATE')")
    public BaseResponse save(@Validated @RequestBody Dictionary dictionary){
        return BaseResponse.getSuccessResponse(dictionaryService.save(dictionary));
    }

    @RecordLog("删除字典信息")
    @DeleteMapping("{id}")
//    @PreAuthorize("hasAnyRole('ADMIN','DICTIONARY_ALL','DICTIONARY_DELETE')")
    public BaseResponse delete(@PathVariable long id){
        dictionaryService.delete(id);
        return BaseResponse.getSuccessResponse();
    }

    @RecordLog("更新字典信息")
    @PutMapping
//    @PreAuthorize("hasAnyRole('ADMIN','DICTIONARY_ALL','DICTIONARY_UPDATE')")
    public BaseResponse update(@Validated(Dictionary.Update.class) @RequestBody Dictionary dictionary){
        return BaseResponse.getSuccessResponse(dictionaryService.update(dictionary));
    }

    @RecordLog("查询字典详情")
    @GetMapping("{id}")
//    @PreAuthorize("hasAnyRole('ADMIN','DICTIONARY_ALL','DICTIONARY_SELECT')")
    public BaseResponse query(@PathVariable long id){
        return BaseResponse.getSuccessResponse(dictionaryService.query(id));
    }

    @RecordLog("查询字典列表")
    @PostMapping("list")
//    @PreAuthorize("hasAnyRole('ADMIN','DICTIONARY_ALL','DICTIONARY_SELECT')")
    public BaseResponse list(@RequestBody JSONObject json, Pageable pageable){
        Page<Dictionary> page = dictionaryService.list(json, pageable);
        return BaseResponse.getSuccessResponse(page);
    }
}
