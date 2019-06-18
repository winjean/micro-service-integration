package com.winjean.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.service.ProcessTaskService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.FormService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.form.DateFormType;
import org.activiti.engine.impl.form.EnumFormType;
import org.activiti.engine.impl.form.TaskFormDataImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProcessTaskServiceImpl implements ProcessTaskService {

    @Autowired
    private TaskService taskService;

    @Autowired
    private FormService formService;

    @Autowired
    private RuntimeService runtimeService;

    @Override
    public BaseResponse claimTask(String taskId, String userId) {
        try {
            taskService.unclaim(taskId);
            taskService.claim(taskId, userId);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return BaseResponse.getFailureResponse(e.getMessage());
        }
        return BaseResponse.getSuccessResponse();
    }

    @Override
    public BaseResponse list(JSONObject param) {
        JSONObject json = new JSONObject();

        try {
            Integer page = param.getInteger("page") == null ? 0 : param.getInteger("page");
            Integer size = param.getInteger("size") == null ? 10 : param.getInteger("size");
            int start = size * page < 0 ? 0 : size * page;

            List list = new ArrayList<>();
            TaskQuery query = taskService.createTaskQuery();

            if (StringUtils.isNotEmpty(param.getString("name"))) {
                query.taskNameLike("%" + param.get("name") + "%");
            }

            if (StringUtils.isNotEmpty(param.getString("processInstanceId"))) {
                query.processInstanceId(param.getString("processInstanceId"));
            }

            List<Task> tasks = query.orderByTaskId().desc().listPage(start, size);
            long total = query.count();
            for (Task task : tasks) {
                Map map = new HashMap<>();
                map.put("id", task.getId());
                map.put("name", task.getName());
                map.put("assignee", task.getAssignee());
                map.put("processInstanceId", task.getProcessInstanceId());
                map.put("createTime", DateFormatUtils.format(task.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                list.add(map);
            }

            json.put("list", list);
            json.put("page", page);
            json.put("total", total);
            json.put("size", size);

        } catch (Exception e) {
            log.error(" query task list failure, query param: {}, message: {}", param, e);

            return BaseResponse.getFailureResponse(e.getMessage());
        }

        return BaseResponse.getSuccessResponse(json);
    }

    @Override
    public BaseResponse backlog(JSONObject param) {
        Integer current = param.getInteger("current") == null ? 1 : param.getInteger("current");
        Integer size = param.getInteger("size") == null ? 10 : param.getInteger("size");
        String userId =param.getString("userId");
        Assert.notNull(userId, "用户编号不能为空");
        int start = size * (current - 1) < 0 ? 0 : size * (current - 1);

        TaskQuery query = taskService.createTaskQuery()
                .taskCandidateOrAssigned(userId);
        List<Task> list = query.orderByTaskCreateTime().desc().listPage(start, size);
        long total = query.count();

        JSONArray array = new JSONArray();
        list.stream().forEach(t -> {
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(t.getProcessInstanceId()).singleResult();
            taskService.getVariable(t.getId(),"tableName");
            JSONObject _json = new JSONObject();
            _json.put("id", t.getId());
            _json.put("name", t.getName());
            _json.put("instanceId", t.getProcessInstanceId());
            _json.put("definitionId", t.getProcessDefinitionId());
            _json.put("definitionKey", t.getTaskDefinitionKey());
            _json.put("assignee", t.getAssignee());
            _json.put("businessKey", processInstance.getBusinessKey());
            _json.put("startUser", taskService.getVariable(t.getId(),"startUser"));
            _json.put("type", taskService.getVariable(t.getId(),"tableName"));
            _json.put("processType", taskService.getVariable(t.getId(),"processType"));
            _json.put("createTime", null == t.getCreateTime() ? null : DateFormatUtils.format(t.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));

            array.add(_json);
        });

        JSONObject json = new JSONObject();
        json.fluentPut("total", total).fluentPut("current", current).fluentPut("size", size).put("list", array);
        return BaseResponse.getSuccessResponse(json);
    }

    @Override
    public BaseResponse taskForm(String instanceId) {

        Task task = taskService.createTaskQuery().processInstanceId(instanceId).singleResult();
        Assert.notNull(task,"流程不存在或已结束, processInstanceId:" + instanceId);

        TaskFormDataImpl taskFormData = (TaskFormDataImpl) formService.getTaskFormData(task.getId());

        List<JSONObject> list = taskFormData.getFormProperties().stream().map(fp ->{

            JSONObject json = new JSONObject()
                    .fluentPut("id", fp.getId())
                    .fluentPut("name", fp.getName())
                    .fluentPut("type", fp.getType())
                    .fluentPut("readable", fp.isReadable())
                    .fluentPut("writable", fp.isWritable())
                    .fluentPut("required", fp.isRequired());

            if(fp.getType() instanceof EnumFormType){
                json.put("value", fp.getType().getInformation("values"));
            }else if(fp.getType() instanceof DateFormType){
                json.put("datePattern",fp.getType().getInformation("datePattern"));
            }
            return json;
        }).collect(Collectors.toList());

        return BaseResponse.getSuccessResponse(list);
    }

    @Override
    public BaseResponse completeTask(Map<String,Object> map) {
        Map<String,Object> _map1 = (Map<String,Object>)map.get("variables");
        Map<String,Object> _map2 = (Map<String,Object>)map.get("completeVariables");
        String processInstanceId = _map1.get("processInstanceId").toString();

        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
        Assert.notNull(task,"任务不存在,流程实例编号:" + processInstanceId);

        taskService.setVariablesLocal(task.getId(),_map1);

        taskService.complete(task.getId(),_map2);

        return BaseResponse.getSuccessResponse(task.getId());
    }
}
