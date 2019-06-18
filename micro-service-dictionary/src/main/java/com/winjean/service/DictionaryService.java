package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.EntityDictionary;
import org.springframework.data.domain.Page;

public interface DictionaryService {

    EntityDictionary save(EntityDictionary dictionary);

    EntityDictionary update(EntityDictionary dictionary);

    boolean delete(long id);

    EntityDictionary query(long id);

    Page<EntityDictionary> list(JSONObject json);
}
