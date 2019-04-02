package com.winjean.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

public interface RoleService {

    void addRole(JSONObject json);

    void updateRole(JSONObject json);

    void deleteRole(JSONObject json);

    JSONObject queryRole(JSONObject json);

    List<JSONObject> queryRoleList(JSONObject json);
}
