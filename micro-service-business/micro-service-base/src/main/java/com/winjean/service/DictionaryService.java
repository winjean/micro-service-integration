package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.Dictionary;
import org.springframework.data.domain.Page;

public interface DictionaryService {

    Dictionary save(Dictionary dictionary);

    Dictionary update(Dictionary dictionary);

    boolean delete(long id);

    Dictionary query(long id);

    Page<Dictionary> list(JSONObject json);
}
