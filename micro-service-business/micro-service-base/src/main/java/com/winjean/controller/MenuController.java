package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.model.entity.EntityMenu;
import com.winjean.model.entity.EntityUser;
import com.winjean.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("menu")
@Slf4j
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping
    public BaseResponse save(@Validated @RequestBody EntityMenu menu){
        return BaseResponse.getSuccessResponse(menuService.save(menu));
    }

    @DeleteMapping("{id}")
    public BaseResponse delete(@PathVariable long id){
        menuService.delete(id);
        return BaseResponse.getSuccessResponse();
    }

    @PutMapping
    public BaseResponse update(@Validated(EntityUser.Update.class) @RequestBody EntityMenu menu){
        return BaseResponse.getSuccessResponse(menuService.update(menu));
    }

    @GetMapping("{id}")
    public BaseResponse query(@PathVariable long id){
        return BaseResponse.getSuccessResponse(menuService.query(id));
    }

    @PostMapping("list")
    public BaseResponse list(@RequestBody JSONObject json){
        Page<EntityMenu> page = menuService.list(json);
        return BaseResponse.getSuccessResponse(page);
    }
}
