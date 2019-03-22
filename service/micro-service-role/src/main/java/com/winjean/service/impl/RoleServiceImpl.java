package com.winjean.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.winjean.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {
    @Override
    public void addRole(JSONObject json) {
        log.info("add role success.");
    }

    @Override
    public void updateRole(JSONObject json) {
        log.info("update role success.");
    }

    @Override
    public void deleteRole(JSONObject json) {
        log.info("delete role success.");
    }

    @Override
    public JSONObject queryRole(JSONObject json) {
        log.info("query role success.");
        return null;
    }

    @Override
    public List<JSONObject> queryRoleList(JSONObject json) {
        log.info("query role list success.");
        return null;
    }
}
