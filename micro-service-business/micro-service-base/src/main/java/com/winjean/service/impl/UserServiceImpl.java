package com.winjean.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.model.request.RequestUserInsert;
import com.winjean.service.UserService;
import com.winjean.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

//    @Resource
//    private UserMapper userMapper;

    @Override
    public BaseResponse insert(RequestUserInsert request) {

        request.setId(StringUtils.getUUID());

//        userMapper.insert(request);
        log.info("add user success.");

        return null;
    }

    @Override
    public BaseResponse update(JSONObject json) {
        log.info("update user success.");
        return null;
    }

    @Override
    public BaseResponse delete(Map<String, Object> map) {
        log.info("delete user success.");
        return null;
    }

    @Override
    public BaseResponse query(JSONObject json) {
        log.info("query user success.");
        return null;
    }

    @Override
    public BaseResponse queryList(JSONObject json) {
        log.info("query user list success.");
        return null;
    }
}
