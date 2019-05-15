package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.EntityDepartment;
import org.springframework.data.domain.Page;

public interface DepartmentService {

    EntityDepartment insert(EntityDepartment department);

    EntityDepartment update(EntityDepartment department);

    boolean delete(long id);

    EntityDepartment query(long id);

    Page<EntityDepartment> list(JSONObject json);
}
