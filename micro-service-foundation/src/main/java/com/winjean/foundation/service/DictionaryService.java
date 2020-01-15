package com.winjean.foundation.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.foundation.domain.Dictionary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DictionaryService {

    Dictionary save(Dictionary dictionary);

    Dictionary update(Dictionary dictionary);

    boolean delete(long id);

    Dictionary query(long id);

    Page<Dictionary> list(JSONObject json, Pageable pageable);
}
