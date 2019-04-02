package com.winjean.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.winjean.service.PermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class PermissionServiceImpl implements PermissionService {
    @Override
    public void addUser(JSONObject json) {
        log.info("add user success.");
    }

    @Override
    public void updateUser(JSONObject json) {
        log.info("update user success.");
    }

    @Override
    public void deleteUser(Map<String, Object> map) {
        log.info("delete user success.");
    }

    @Override
    public JSONObject queryUser(JSONObject json) {
        log.info("query user success.");
        return null;
    }

    @Override
    public List<JSONObject> queryUserList(JSONObject json) {
        log.info("query user list success.");
        return null;
    }
}
