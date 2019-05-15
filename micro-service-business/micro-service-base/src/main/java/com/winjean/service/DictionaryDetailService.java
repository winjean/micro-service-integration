package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.EntityDictionaryDetail;
import org.springframework.data.domain.Page;

public interface DictionaryDetailService {

    EntityDictionaryDetail save(EntityDictionaryDetail dictionaryDetail);

    EntityDictionaryDetail update(EntityDictionaryDetail dictionaryDetail);

    boolean delete(long id);

    EntityDictionaryDetail query(long id);

    Page<EntityDictionaryDetail> list(JSONObject json);
}
