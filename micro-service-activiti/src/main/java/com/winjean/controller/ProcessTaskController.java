package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.service.ProcessTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("process/task")
public class ProcessTaskController {

    @Autowired
    private ProcessTaskService processTaskService;

    /**
     * 任务签收
     * @param taskId
     * @param userId
     * @return
     */
    @GetMapping("claim/{taskId}/{userId}")
    public BaseResponse history(@PathVariable String taskId, @PathVariable String userId) {

        return processTaskService.claimTask(taskId, userId);
    }

    /**
     * 用户任务列表
     */
    @PostMapping("list")
    public BaseResponse list(@RequestBody JSONObject param) {
        return processTaskService.list(param);
    }

    /**
     * 待办任务
     * @param param
     * @return
     */
    @PostMapping("backlog")
    public BaseResponse backlog(@RequestBody JSONObject param) {
        try{
            return processTaskService.backlog(param);
        }catch (Exception e){
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }

    /**
     * 根据流程编号获取当前任务窗体表单
     * @param instanceId
     * @return
     */
    @GetMapping("taskForm/{instanceId}")
    public BaseResponse taskForm(@PathVariable String instanceId) {
        try{
            return processTaskService.taskForm(instanceId);
        }catch (Exception e){
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }

    /**
     * 完成当前流程正在运行的任务
     * @param map
     * @return
     */
    @PostMapping("completeTask")
    public BaseResponse completeTask(@RequestBody Map<String,Object> map) {
        try{
            return processTaskService.completeTask(map);
        }catch (Exception e){
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }
}
