package com.winjean.foundation.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.foundation.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService /*extends UserDetailsService*/ {

    User save(User user);

    User update(User user);

    boolean delete(long id);

    User query(long id);

    Page<User> list(JSONObject json, Pageable pageable);

}
