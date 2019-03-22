package com.winjean.fallback;

import com.alibaba.fastjson.JSONObject;
import com.winjean.feignClient.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserFallback implements UserFeignClient {

    @Override
    public void addUser(JSONObject json) {
        log.info("invoke error, add user request json : {} ",json);
    }

    @Override
    public void deleteUser(JSONObject json) {
        log.info("invoke error, delete user request json : {} ",json);
    }

    @Override
    public void updateUser(JSONObject json) {
        log.info("invoke error, update user request json : {} ",json);
    }

    @Override
    public JSONObject queryUser(JSONObject json) {
        log.info("invoke error, query user request json : {} ",json);
        return null;
    }

    @Override
    public List<JSONObject> queryUserList(JSONObject json) {
        log.info("invoke error, query user list request json : {} ",json);
        return null;
    }
}
