package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.model.entity.Permission;
import com.winjean.model.entity.User;
import com.winjean.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("permission")
@Slf4j
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_CREATE')")
    public BaseResponse save(@Validated @RequestBody Permission permission){
        return BaseResponse.getSuccessResponse(permissionService.save(permission));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_DELETE')")
    public BaseResponse delete(@PathVariable long id){
        permissionService.delete(id);
        return BaseResponse.getSuccessResponse();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_UPDATE')")
    public BaseResponse update(@Validated(User.Update.class) @RequestBody Permission permission){
        return BaseResponse.getSuccessResponse(permissionService.update(permission));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_SELECT')")
    public BaseResponse query(@PathVariable long id){
        return BaseResponse.getSuccessResponse(permissionService.query(id));
    }

    @PostMapping("list")
    @PreAuthorize("hasAnyRole('ADMIN','PERMISSION_ALL','PERMISSION_SELECT')")
    public BaseResponse list(@RequestBody JSONObject json){
        Page<Permission> page = permissionService.list(json);
        return BaseResponse.getSuccessResponse(page);
    }
}
