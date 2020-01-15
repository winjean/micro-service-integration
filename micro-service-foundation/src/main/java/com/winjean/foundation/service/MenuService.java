package com.winjean.foundation.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.foundation.domain.Menu;
import org.springframework.data.domain.Page;

public interface MenuService {

    Menu save(Menu menu);

    Menu update(Menu menu);

    boolean delete(long id);

    Menu query(long id);

    Page<Menu> list(JSONObject json);
}
