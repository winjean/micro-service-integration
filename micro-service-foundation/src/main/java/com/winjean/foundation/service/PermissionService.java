package com.winjean.foundation.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.foundation.domain.Permission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PermissionService {

    Permission save(Permission permission);

    Permission update(Permission permission);

    boolean delete(long id);

    Permission query(long id);

    Page<Permission> list(JSONObject json, Pageable pageable);
}
