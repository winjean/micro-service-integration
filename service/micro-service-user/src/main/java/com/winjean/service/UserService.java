package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.request.RequestUserInsert;

import java.util.List;
import java.util.Map;

public interface UserService {

    void insert(RequestUserInsert request);

    void update(JSONObject json);

    void delete(Map<String, Object> map);

    JSONObject query(JSONObject json);

    List<JSONObject> queryList(JSONObject json);
}
