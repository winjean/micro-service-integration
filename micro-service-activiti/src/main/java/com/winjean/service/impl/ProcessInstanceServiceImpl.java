package com.winjean.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.service.ProcessInstanceService;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.engine.impl.context.Context;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProcessInstanceServiceImpl implements ProcessInstanceService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private ProcessEngine processEngine;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private ProcessEngineConfiguration processEngineConfiguration;

    @Override
    public BaseResponse list(JSONObject param) {

        Integer page = param.getInteger("page") == null ? 0 : param.getInteger("page");
        Integer size = param.getInteger("size") == null ? 10 : param.getInteger("size");
        int start = size * page < 0 ? 0 : size * page;

        ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();

        if (StringUtils.isNotEmpty(param.getString("name"))) {
            processInstanceQuery.processInstanceNameLike("%" + param.get("name") + "%");
        }

        List<ProcessInstance> list = processInstanceQuery.listPage(start, size);
        long total = processInstanceQuery.count();
        JSONArray array = new JSONArray();
        list.stream().forEach( pi ->{

            Task task =taskService.createTaskQuery().processInstanceId(pi.getProcessInstanceId()).singleResult();

            JSONObject _json = new JSONObject();
            _json.put("id",pi.getId());
            _json.put("name",pi.getName());
            _json.put("businessKey",pi.getBusinessKey());
            _json.put("processDefinitionId",pi.getProcessDefinitionId());
            _json.put("deploymentId",pi.getDeploymentId());
            _json.put("taskName",task.getName());
            _json.put("assignee",task.getAssignee());

            array.add(_json);
        });

        JSONObject json = new JSONObject();
        json.put("total",total);
        json.put("page",page);
        json.put("size",size);
        json.put("list",array);
        return BaseResponse.getSuccessResponse(json);
    }

    @Override
    public BaseResponse delete(String instanceId) {
        runtimeService.deleteProcessInstance(instanceId, "删除流程实例[" + instanceId + "]");
        return BaseResponse.getSuccessResponse();
    }

    @Override
    public BaseResponse flowChart(String processInstanceId) throws IOException {
        List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);

        String procDefId = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult().getProcessDefinitionId();
        BpmnModel bpmnModel = processEngine.getRepositoryService().getBpmnModel(procDefId);

        Context.setProcessEngineConfiguration((ProcessEngineConfigurationImpl) processEngineConfiguration);

        ProcessDiagramGenerator diagramGenerator = processEngineConfiguration.getProcessDiagramGenerator();
        @Cleanup InputStream imageStream = diagramGenerator.generateDiagram(
                bpmnModel,"png", activeActivityIds, new ArrayList<String>(),"宋体","宋体","宋体",null,1.0);

        byte[] b = new byte[1024];
        @Cleanup ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        int len;
        while (( len = imageStream.read(b, 0, b.length)) != -1) {
            byteStream.write(b, 0, len);
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return BaseResponse.getSuccessResponse(encoder.encode(byteStream.toByteArray()));
    }

    @Override
    public BaseResponse history(String processInstanceId) {

        JSONObject result = new JSONObject();

        List<HistoricVariableInstance> instanceVariables = historyService.createHistoricVariableInstanceQuery().processInstanceId(processInstanceId).list();
        if(! instanceVariables.isEmpty()){
            List<JSONObject> list = instanceVariables.stream().map(hvi ->
                    new JSONObject()
                            .fluentPut("variableName", hvi.getVariableName())
                            .fluentPut("value", hvi.getValue())
                            .fluentPut("typeName", hvi.getVariableTypeName())
            ).collect(Collectors.toList());
            result.put("instanceVariables", list);
        }

        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId).orderByTaskCreateTime().asc().list();

        List<JSONObject> tasks = list.stream().map(h ->{
            JSONObject _json = new JSONObject();
            _json.put("taskId",h.getId());
            _json.put("taskName",h.getName());
            _json.put("assignee",h.getAssignee());
            _json.put("startTime",h.getStartTime());
            _json.put("endTime",h.getEndTime());

            List<HistoricVariableInstance> taskVariable = historyService.createHistoricVariableInstanceQuery().taskId(h.getId()).list();
            if(! taskVariable.isEmpty()){
                _json.put("taskVariable",taskVariable.stream().map(hvi ->
                        new JSONObject().fluentPut("variableName", hvi.getVariableName())
                                .fluentPut("value", hvi.getValue())
                                .fluentPut("typeName", hvi.getVariableTypeName())
                ).collect(Collectors.toList()));
            }

            return _json;
        }).collect(Collectors.toList());

        result.put("task", tasks);

        return BaseResponse.getSuccessResponse(result);
    }

    @Override
    public BaseResponse startByMe(JSONObject param) {

        Integer current = param.getInteger("current") == null ? 1 : param.getInteger("current");
        Integer size = param.getInteger("size") == null ? 10 : param.getInteger("size");
        String userId = param.getString("userId");
        int start = size * (current - 1) < 0 ? 0 : size * (current - 1);

        HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery().startedBy(userId);
        List<HistoricProcessInstance> list =query.orderByProcessInstanceStartTime().desc().listPage(start,size);
        long total = query.count();
        JSONArray array = new JSONArray();
        list.stream().forEach(l ->{
            JSONObject _json = new JSONObject();
            _json.put("id",l.getId());
            _json.put("name",l.getName());
            _json.put("businessKey",l.getBusinessKey());
            _json.put("deploymentId",l.getDeploymentId());
            _json.put("createTime",null == l.getStartTime() ? null : DateFormatUtils.format(l.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
            _json.put("endTime",null == l.getEndTime() ? null : DateFormatUtils.format(l.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
            array.add(_json);
        });

        JSONObject json = new JSONObject();
        json.fluentPut("total",total).fluentPut("current",current).fluentPut("size",size).put("list",array);
        return BaseResponse.getSuccessResponse(json);
    }
}
