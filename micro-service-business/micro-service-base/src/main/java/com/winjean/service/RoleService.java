package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.Role;
import org.springframework.data.domain.Page;

public interface RoleService {

    Role save(Role role);

    Role update(Role role);

    boolean delete(long id);

    Role query(long id);

    Page<Role> list(JSONObject json);
}
