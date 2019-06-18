package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public interface ProcessDefService {

    /**
     * 流程定义列表
     *
     * @return
     */
    BaseResponse list(JSONObject param);

    /**
     * 删除流程定义
     * @param deploymentId
     * @return
     */
    BaseResponse delete(String deploymentId);

    /**
     * 根据流程定义启动一个流程实例
     * @param procDefKey
     * @return
     */
    BaseResponse start(String procDefKey, String businessKey, Map<String, Object> map);

    /**
     * 获取当前流程的流程图
     */
    BaseResponse flowChart(String procDefId) throws IOException;

    /**
     * 下载流程模型
     * @param processDefinitionId
     * @param type
     * @param response
     */
    void download(String processDefinitionId, String type, HttpServletResponse response) throws Exception;
}
