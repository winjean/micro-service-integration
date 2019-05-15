package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.EntityMenu;
import org.springframework.data.domain.Page;

public interface MenuService {

    EntityMenu save(EntityMenu menu);

    EntityMenu update(EntityMenu menu);

    boolean delete(long id);

    EntityMenu query(long id);

    Page<EntityMenu> list(JSONObject json);
}
