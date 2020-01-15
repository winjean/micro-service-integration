package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User save(User user);

    User update(User user);

    boolean delete(long id);

    User query(long id);

    Page<User> list(JSONObject json);

}
