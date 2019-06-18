package com.winjean.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.service.ProcessDefService;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.image.ProcessDiagramGenerator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ProcessDefServiceImpl implements ProcessDefService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private ProcessEngineConfiguration processEngineConfiguration;

    @Override
    public BaseResponse list(JSONObject param) {

        Integer page = param.getInteger("page") == null ? 0 : param.getInteger("page");
        Integer size = param.getInteger("size") == null ? 10 : param.getInteger("size");
        int start = size * page < 0 ? 0 : size * page;

        ProcessDefinitionQuery definitionQuery = repositoryService.createProcessDefinitionQuery();
        if (StringUtils.isNotEmpty(param.getString("name"))) {
            definitionQuery.processDefinitionNameLike("%" + param.get("name") + "%");
        }

        if (StringUtils.isNotEmpty(param.getString("key"))) {
            definitionQuery.processDefinitionKeyLike("%" + param.get("key") + "%");
        }

        ProcessDefinitionQuery processDefinitionQuery = definitionQuery.orderByProcessDefinitionKey();
        if (Boolean.valueOf(param.getString("latestVersion"))) {
            processDefinitionQuery.latestVersion();
        }
        processDefinitionQuery.orderByProcessDefinitionVersion().desc();


        List<ProcessDefinition> processDefinitionList = processDefinitionQuery.listPage(start, size);
        long total = processDefinitionQuery.count();

        JSONArray array = new JSONArray();

        processDefinitionList.stream().forEach(processDefinition ->{
            JSONObject _json = new JSONObject();
            String deploymentId = processDefinition.getDeploymentId();
            Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).orderByDeploymentId().desc().singleResult();

            _json.put("id", processDefinition.getId());
            _json.put("name", processDefinition.getName());
            _json.put("version", processDefinition.getVersion());
            _json.put("key", processDefinition.getKey());
            _json.put("deploymentId", processDefinition.getDeploymentId());
            _json.put("suspended", processDefinition.isSuspended());
            _json.put("resource", processDefinition.getResourceName());
            _json.put("image", processDefinition.getDiagramResourceName());
            _json.put("deploymentTime", DateFormatUtils.format(deployment.getDeploymentTime(), "yyyy-MM-dd HH:mm:ss"));

            array.add(_json);
        });

        // 组装分页数据
        JSONObject json = new JSONObject();

        json.put("total",total);
        json.put("page",page);
        json.put("size",size);
        json.put("list",array);
        return BaseResponse.getSuccessResponse(json);
    }

    @Override
    public BaseResponse delete(String deploymentId) {

        repositoryService.deleteDeployment(deploymentId);
        return BaseResponse.getSuccessResponse();
    }

    @Override
    public BaseResponse start(String procDefKey, String businessKey, Map<String,Object> map) {
        ProcessInstance processInstance = StringUtils.isEmpty(businessKey)
                ? runtimeService.startProcessInstanceByKey(procDefKey)
                : runtimeService.startProcessInstanceByKey(procDefKey, businessKey, map);
        String startUser = map.get("startUser").toString();
        String processName = repositoryService.createProcessDefinitionQuery().processDefinitionKey(procDefKey).latestVersion().singleResult().getName();

        runtimeService.setProcessInstanceName(processInstance.getProcessInstanceId(),"用户【" + startUser + "】发起的【" + processName + "】");
        return BaseResponse.getSuccessResponse(processInstance.getId());
    }

    @Override
    public BaseResponse flowChart(String procDefId) throws IOException {

        BpmnModel bpmnModel = processEngine.getRepositoryService().getBpmnModel(procDefId);
        Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);

        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        @Cleanup InputStream imageStream = diagramGenerator.generateDiagram(
                bpmnModel,"png", new ArrayList<String>(), new ArrayList<String>(),
                processEngineConfiguration.getActivityFontName(),
                processEngineConfiguration.getLabelFontName(),
                processEngineConfiguration.getAnnotationFontName(),null,1.0);

        byte[] b = new byte[1024];
        @Cleanup ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int len;
        while ((len = imageStream.read(b, 0, b.length)) != -1) {
            byteStream.write(b, 0, len);
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return BaseResponse.getSuccessResponse(encoder.encode(byteStream.toByteArray()));
    }

    @Override
    public void download(String processDefinitionId, String type, HttpServletResponse response) throws Exception {

        BpmnModel model = repositoryService.getBpmnModel(processDefinitionId);

        if("xml".equalsIgnoreCase(type)){
            String filename = model.getMainProcess().getId() + ".bpmn20.xml";
            byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);

            @Cleanup ByteArrayInputStream inputStream = new ByteArrayInputStream(bpmnBytes);
            @Cleanup OutputStream os = response.getOutputStream();

            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
            StreamUtils.copy(inputStream, os);
        } else if("png".equalsIgnoreCase(type)){
            @Cleanup InputStream inputStream = repositoryService.getProcessDiagram(processDefinitionId);
            String filename = model.getMainProcess().getId() + ".png";
            @Cleanup OutputStream os = response.getOutputStream();

            response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
            StreamUtils.copy(inputStream, os);
        }else{
            throw new RuntimeException("type must in [ xml|png ]");
        }
    }
}
