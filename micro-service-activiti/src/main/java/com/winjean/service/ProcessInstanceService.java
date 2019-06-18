package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;

import java.io.IOException;

public interface ProcessInstanceService {

    /**
     * 流程实例列表
     */
    BaseResponse list(JSONObject param);

    /**
     * 删除流程实例
     */
    BaseResponse delete(String instanceId);

    /**
     * 获取当前流程实例的流程图
     */
    BaseResponse flowChart(String instanceId) throws IOException;

    /**
     * 获取当前流程实例的历史信息
     * @param instanceId
     * @return
     */
    BaseResponse history(String instanceId);

    /**
     * 由我发起的流程
     * @return
     */
    BaseResponse startByMe(JSONObject param);
}
