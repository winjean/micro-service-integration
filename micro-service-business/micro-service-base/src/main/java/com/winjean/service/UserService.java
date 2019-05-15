package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.EntityUser;
import org.springframework.data.domain.Page;

public interface UserService {

    EntityUser insert(EntityUser user);

    EntityUser update(EntityUser user);

    boolean delete(long id);

    EntityUser query(long id);

    Page<EntityUser> list(JSONObject json);
}
