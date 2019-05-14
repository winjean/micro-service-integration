package com.winjean.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.request.RequestUserInsert;
import com.winjean.service.UserService;
import com.winjean.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

//    @Resource
//    private UserMapper userMapper;

    @Override
    public void insert(RequestUserInsert request) {

        request.setId(StringUtils.getUUID());

//        userMapper.insert(request);
        log.info("add user success.");
    }

    @Override
    public void update(JSONObject json) {
        log.info("update user success.");
    }

    @Override
    public void delete(Map<String, Object> map) {
        log.info("delete user success.");
    }

    @Override
    public JSONObject query(JSONObject json) {
        log.info("query user success.");
        return null;
    }

    @Override
    public List<JSONObject> queryList(JSONObject json) {
        log.info("query user list success.");
        return null;
    }
}
