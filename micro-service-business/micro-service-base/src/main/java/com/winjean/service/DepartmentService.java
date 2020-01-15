package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.Department;
import org.springframework.data.domain.Page;

public interface DepartmentService {

    Department save(Department department);

    Department update(Department department);

    boolean delete(long id);

    Department query(long id);

    Page<Department> list(JSONObject json);
}
