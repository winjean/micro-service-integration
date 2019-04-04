package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.ModuleEntity;
import com.winjean.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @PostMapping("save")
    public Object save(@RequestBody ModuleEntity entity){
        moduleService.save(entity);
        return "save success";
    }

    @PostMapping("delete")
    public Object delete(@RequestParam int id){
        moduleService.delete(id);
        return "delete success";
    }

    @PostMapping("update")
    public Object findModuleById(@RequestBody ModuleEntity entity){

        moduleService.update(entity);
        return "update success";
    }

    @PostMapping("findById")
    public Object findModuleById(@RequestBody JSONObject json){
        int id = json.getInteger("id");

        ModuleEntity module =  moduleService.findById(id);
        return module;
    }

    @PostMapping("findByName")
    public Object findModuleByName(@RequestBody JSONObject json){
        String name = json.getString("name");
        ModuleEntity module = moduleService.findByName(name);
        return module;
    }

    @PostMapping("findAll")
    public Object findAll(@RequestBody JSONObject json){
        int pageNo = json.getInteger("pageNo");
        int pageSize = json.getInteger("pageSize");
        Page<ModuleEntity> modules = moduleService.findAll(pageNo, pageSize);
        return modules;
    }
}
