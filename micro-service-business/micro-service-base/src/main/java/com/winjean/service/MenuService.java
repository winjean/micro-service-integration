package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.Menu;
import org.springframework.data.domain.Page;

public interface MenuService {

    Menu save(Menu menu);

    Menu update(Menu menu);

    boolean delete(long id);

    Menu query(long id);

    Page<Menu> list(JSONObject json);
}
