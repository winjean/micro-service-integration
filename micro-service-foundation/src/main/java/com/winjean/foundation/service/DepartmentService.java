package com.winjean.foundation.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.foundation.domain.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {

    Department save(Department department);

    Department update(Department department);

    boolean delete(long id);

    Department query(long id);

    Page<Department> list(JSONObject json, Pageable pageable);
}
