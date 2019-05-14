package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.model.request.RequestUserInsert;

import java.util.Map;

public interface UserService {

    BaseResponse insert(RequestUserInsert request);

    BaseResponse update(JSONObject json);

    BaseResponse delete(Map<String, Object> map);

    BaseResponse query(JSONObject json);

    BaseResponse queryList(JSONObject json);
}
