package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.DictionaryDetail;
import org.springframework.data.domain.Page;

public interface DictionaryDetailService {

    DictionaryDetail save(DictionaryDetail dictionaryDetail);

    DictionaryDetail update(DictionaryDetail dictionaryDetail);

    boolean delete(long id);

    DictionaryDetail query(long id);

    Page<DictionaryDetail> list(JSONObject json);
}
