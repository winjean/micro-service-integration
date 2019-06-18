package com.winjean.service;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import org.activiti.engine.repository.Deployment;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface ModelService {

    /**
     * 模型列表
     */
    BaseResponse list(JSONObject param);

    /**
     * 创建模型
     */
    BaseResponse create(JSONObject model);

    /**
     * 根据Model部署流程
     */
    BaseResponse deploy(String modelId);

    /**
     * 根据Model部署流程
     */
    Deployment deployFromFile(MultipartFile xmlFile, MultipartFile pngFile, String deploymentName) throws Exception;

    /**
     * delete model
     */
    BaseResponse delete(String modelId);

    /**
     * 查询监听器类型
     * @param type
     * @return
     */
    BaseResponse queryListeners(String type);

    /**
     * 下载流程模型
     * @param modelId
     * @param response
     */
    void download(String modelId, HttpServletResponse response) throws Exception;

    /**
     * 流程定义转流程模型
     * @param processDefinitionId
     */
    void convertToModel(String processDefinitionId) throws Exception;
}
