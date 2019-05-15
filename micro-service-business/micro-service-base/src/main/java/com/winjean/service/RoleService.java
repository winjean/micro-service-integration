package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.EntityRole;
import org.springframework.data.domain.Page;

public interface RoleService {

    EntityRole save(EntityRole role);

    EntityRole update(EntityRole role);

    boolean delete(long id);

    EntityRole query(long id);

    Page<EntityRole> list(JSONObject json);
}
