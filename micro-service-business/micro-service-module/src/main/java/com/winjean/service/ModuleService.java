package com.winjean.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface ModuleService {

    void addUser(JSONObject json);

    void updateUser(JSONObject json);

    void deleteUser(Map<String, Object> map);

    JSONObject queryUser(JSONObject json);

    List<JSONObject> queryUserList(JSONObject json);
}
