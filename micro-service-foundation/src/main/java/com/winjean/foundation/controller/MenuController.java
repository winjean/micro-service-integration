package com.winjean.foundation.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.foundation.domain.Menu;
import com.winjean.foundation.service.MenuService;
import com.winjean.logging.annotation.RecordLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("menu")
@Slf4j
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RecordLog("新增菜单信息")
    @PostMapping
//    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_CREATE')")
    public BaseResponse save(@Validated @RequestBody Menu menu){
        return BaseResponse.getSuccessResponse(menuService.save(menu));
    }

    @RecordLog("删除菜单信息")
    @DeleteMapping("{id}")
//    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_DELETE')")
    public BaseResponse delete(@PathVariable long id){
        menuService.delete(id);
        return BaseResponse.getSuccessResponse();
    }

    @RecordLog("更新菜单信息")
    @PutMapping
//    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_UPDATE')")
    public BaseResponse update(@Validated(Menu.Update.class) @RequestBody Menu menu){
        return BaseResponse.getSuccessResponse(menuService.update(menu));
    }

    @RecordLog("查询菜单详情")
    @GetMapping("{id}")
//    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_SELECT')")
    public BaseResponse query(@PathVariable long id){
        return BaseResponse.getSuccessResponse(menuService.query(id));
    }

    @RecordLog("查询菜单列表")
    @PostMapping("list")
//    @PreAuthorize("hasAnyRole('ADMIN','MENU_ALL','MENU_SELECT')")
    public BaseResponse list(@RequestBody JSONObject json, Pageable pageable){
        Page<Menu> page = menuService.list(json, pageable);
        return BaseResponse.getSuccessResponse(page);
    }
}
