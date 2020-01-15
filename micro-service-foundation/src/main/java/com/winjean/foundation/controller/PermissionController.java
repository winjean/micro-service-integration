package com.winjean.foundation.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.foundation.domain.Permission;
import com.winjean.foundation.service.PermissionService;
import com.winjean.logging.annotation.RecordLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("permission")
@Slf4j
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RecordLog("新增权限信息")
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_CREATE')")
    public BaseResponse save(@Validated @RequestBody Permission permission){
        return BaseResponse.getSuccessResponse(permissionService.save(permission));
    }
    @RecordLog("删除权限信息")
    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_DELETE')")
    public BaseResponse delete(@PathVariable long id){
        permissionService.delete(id);
        return BaseResponse.getSuccessResponse();
    }

    @RecordLog("更新权限信息")
    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_UPDATE')")
    public BaseResponse update(@Validated(Permission.Update.class) @RequestBody Permission permission){
        return BaseResponse.getSuccessResponse(permissionService.update(permission));
    }

    @RecordLog("查询权限详情")
    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_SELECT')")
    public BaseResponse query(@PathVariable long id){
        return BaseResponse.getSuccessResponse(permissionService.query(id));
    }

    @RecordLog("查询权限列表")
    @PostMapping("list")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_SELECT')")
    public BaseResponse list(@RequestBody JSONObject json, Pageable pageable){
        Page<Permission> page = permissionService.list(json, pageable);
        return BaseResponse.getSuccessResponse(page);
    }
}
