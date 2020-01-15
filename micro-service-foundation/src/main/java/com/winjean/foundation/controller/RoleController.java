package com.winjean.foundation.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.foundation.domain.Role;
import com.winjean.foundation.service.RoleService;
import com.winjean.logging.annotation.RecordLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("role")
@Slf4j
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    @RecordLog("新增角色信息")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_ALL','ROLE_CREATE')")
    public BaseResponse save(@Validated @RequestBody Role role){
        return BaseResponse.getSuccessResponse(roleService.save(role));
    }

    @RecordLog("删除角色信息")
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_ALL','ROLE_DELETE')")
    public BaseResponse delete(@PathVariable long id){
        roleService.delete(id);
        return BaseResponse.getSuccessResponse();
    }

    @RecordLog("更新角色信息")
    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_ALL','ROLE_UPDATE')")
    public BaseResponse update(@Validated(Role.Update.class) @RequestBody Role role){
        return BaseResponse.getSuccessResponse(roleService.update(role));
    }

    @RecordLog("查询角色详情")
    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_ALL','ROLE_SELECT')")
    public BaseResponse query(@PathVariable long id){
        return BaseResponse.getSuccessResponse(roleService.query(id));
    }

    @RecordLog("查询角色列表")
    @PostMapping("list")
    @PreAuthorize("hasAnyRole('ADMIN','ROLE_ALL','ROLE_SELECT')")
    public BaseResponse list(@RequestBody JSONObject json, Pageable pageable){
        Page<Role> page = roleService.list(json, pageable);
        return BaseResponse.getSuccessResponse(page);
    }
}
