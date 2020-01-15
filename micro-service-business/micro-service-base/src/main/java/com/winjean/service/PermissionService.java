package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.Permission;
import org.springframework.data.domain.Page;

public interface PermissionService {

    Permission save(Permission permission);

    Permission update(Permission permission);

    boolean delete(long id);

    Permission query(long id);

    Page<Permission> list(JSONObject json);
}
