package com.winjean.foundation.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.foundation.domain.Department;
import com.winjean.foundation.domain.User;
import com.winjean.foundation.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("department")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','DEPARTMENT_ALL','DEPARTMENT_CREATE')")
    public BaseResponse save(@Validated @RequestBody Department department){
        return BaseResponse.getSuccessResponse(departmentService.save(department));
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DEPARTMENT_ALL','DEPARTMENT_DELETE')")
    public BaseResponse delete(@PathVariable long id){
        departmentService.delete(id);
        return BaseResponse.getSuccessResponse();
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('ADMIN','DEPARTMENT_ALL','DEPARTMENT_UPDATE')")
    public BaseResponse update(@Validated(User.Update.class) @RequestBody Department department){
        return BaseResponse.getSuccessResponse(departmentService.update(department));
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DEPARTMENT_ALL','DEPARTMENT_SELECT')")
    public BaseResponse query(@PathVariable long id){
        return BaseResponse.getSuccessResponse(departmentService.query(id));
    }

    @PostMapping("list")
    @PreAuthorize("hasAnyRole('ADMIN','DEPARTMENT_ALL','DEPARTMENT_SELECT')")
    public BaseResponse list(@RequestBody JSONObject json){
        Page<Department> page = departmentService.list(json);
        return BaseResponse.getSuccessResponse(page);
    }
}
