package com.winjean.feignClient;

import com.alibaba.fastjson.JSONObject;
import com.winjean.fallback.UserFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(value = "micro-service-user",fallback = UserFallback.class)
public interface UserFeignClient {

    @PostMapping("user/add")
    void addUser(@RequestBody JSONObject json);

    @PostMapping("user/delete")
    void deleteUser(@RequestBody JSONObject json);

    @PostMapping("user/update")
    void updateUser(@RequestBody JSONObject json);

    @PostMapping("user/query")
    JSONObject queryUser(@RequestBody JSONObject json);

    @PostMapping("user/queryList")
    List<JSONObject> queryUserList(@RequestBody JSONObject json);
}
