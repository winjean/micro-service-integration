package com.winjean.foundation.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.foundation.domain.Department;
import com.winjean.foundation.domain.User;
import com.winjean.foundation.service.DepartmentService;
import com.winjean.logging.annotation.RecordLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @RecordLog("新增部门信息")
    @PreAuthorize("hasAnyRole('ADMIN','DEPARTMENT_ALL','DEPARTMENT_CREATE')")
    public BaseResponse save(@Validated @RequestBody Department department){
        return BaseResponse.getSuccessResponse(departmentService.save(department));
    }

    @DeleteMapping("{id}")
    @RecordLog("删除部门信息")
    @PreAuthorize("hasAnyRole('ADMIN','DEPARTMENT_ALL','DEPARTMENT_DELETE')")
    public BaseResponse delete(@PathVariable long id){
        departmentService.delete(id);
        return BaseResponse.getSuccessResponse();
    }

    @PutMapping
    @RecordLog("修改部门信息")
    @PreAuthorize("hasAnyRole('ADMIN','DEPARTMENT_ALL','DEPARTMENT_UPDATE')")
    public BaseResponse update(@Validated(User.Update.class) @RequestBody Department department){
        return BaseResponse.getSuccessResponse(departmentService.update(department));
    }

    @GetMapping("{id}")
    @RecordLog("查询部门详细信息")
    @PreAuthorize("hasAnyRole('ADMIN','DEPARTMENT_ALL','DEPARTMENT_SELECT')")
    public BaseResponse query(@PathVariable long id){
        return BaseResponse.getSuccessResponse(departmentService.query(id));
    }

    @PostMapping("list")
    @RecordLog("查询部门列表")
    @PreAuthorize("hasAnyRole('ADMIN','DEPARTMENT_ALL','DEPARTMENT_SELECT')")
    public BaseResponse list(@RequestBody JSONObject json, Pageable pageable){
        Page<Department> page = departmentService.list(json, pageable);
        return BaseResponse.getSuccessResponse(page);
    }
}
