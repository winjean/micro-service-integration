package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;

import java.util.Map;

public interface ProcessTaskService {

    /**
     * 签收任务
     * @param taskId
     * @param userId
     * @return
     */
    BaseResponse claimTask(String taskId, String userId);

    /**
     * 用户任务列表
     */
    BaseResponse list(JSONObject param);

    /**
     * 我的待办任务
     * @param param
     * @return
     */
    BaseResponse backlog(JSONObject param);

    /**
     * 获取流程当前任务表单
     * @param instanceId
     * @return
     */
    BaseResponse taskForm(String instanceId);

    /**
     * 获取流程当前任务表单
     * @param map
     * @return
     */
    BaseResponse completeTask(Map<String, Object> map);
}
