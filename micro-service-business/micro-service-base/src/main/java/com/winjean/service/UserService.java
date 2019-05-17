package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.EntityUser;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    EntityUser save(EntityUser user);

    EntityUser update(EntityUser user);

    boolean delete(long id);

    EntityUser query(long id);

    Page<EntityUser> list(JSONObject json);

}
