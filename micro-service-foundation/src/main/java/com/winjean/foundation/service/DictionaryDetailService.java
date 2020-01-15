package com.winjean.foundation.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.foundation.domain.DictionaryDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DictionaryDetailService {

    DictionaryDetail save(DictionaryDetail dictionaryDetail);

    DictionaryDetail update(DictionaryDetail dictionaryDetail);

    boolean delete(long id);

    DictionaryDetail query(long id);

    Page<DictionaryDetail> list(JSONObject json, Pageable pageable);
}
