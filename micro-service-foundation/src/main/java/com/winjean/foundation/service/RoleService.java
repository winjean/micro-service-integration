package com.winjean.foundation.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.foundation.domain.Role;
import org.springframework.data.domain.Page;

public interface RoleService {

    Role save(Role role);

    Role update(Role role);

    boolean delete(long id);

    Role query(long id);

    Page<Role> list(JSONObject json);
}
