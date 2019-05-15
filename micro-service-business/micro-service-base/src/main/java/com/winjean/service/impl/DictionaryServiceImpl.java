package com.winjean.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.winjean.model.entity.EntityDictionary;
import com.winjean.service.DictionaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DictionaryServiceImpl implements DictionaryService {

//    @Resource
//    private UserMapper userMapper;

    @Override
    public EntityDictionary insert(EntityDictionary request) {

//        userMapper.insert(request);
        log.info("add user success.");

        return null;
    }

    @Override
    public EntityDictionary update(EntityDictionary json) {
        log.info("update user success.");
        return null;
    }

    @Override
    public boolean delete(long id) {
        log.info("delete user success.");
        return true;
    }

    @Override
    public EntityDictionary query(long id) {
        log.info("query user success.");
        return null;
    }

    @Override
    public Page<EntityDictionary> list(JSONObject json) {
        log.info("query user list success.");
        return null;
    }
}
