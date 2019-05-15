package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.model.entity.EntityDepartment;
import com.winjean.model.entity.EntityUser;
import com.winjean.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("department")
@Slf4j
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @PostMapping
    public BaseResponse save(@Validated @RequestBody EntityDepartment department){
        return BaseResponse.getSuccessResponse(departmentService.save(department));
    }

    @DeleteMapping("{id}")
    public BaseResponse delete(@PathVariable long id){
        departmentService.delete(id);
        return BaseResponse.getSuccessResponse();
    }

    @PutMapping
    public BaseResponse update(@Validated(EntityUser.Update.class) @RequestBody EntityDepartment department){
        return BaseResponse.getSuccessResponse(departmentService.update(department));
    }

    @GetMapping("{id}")
    public BaseResponse query(@PathVariable long id){
        return BaseResponse.getSuccessResponse(departmentService.query(id));
    }

    @PostMapping("list")
    public BaseResponse list(@RequestBody JSONObject json){
        Page<EntityDepartment> page = departmentService.list(json);
        return BaseResponse.getSuccessResponse(page);
    }
}
