package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.EntityPermission;
import org.springframework.data.domain.Page;

public interface PermissionService {

    EntityPermission insert(EntityPermission permission);

    EntityPermission update(EntityPermission permission);

    boolean delete(long id);

    EntityPermission query(long id);

    Page<EntityPermission> list(JSONObject json);
}
