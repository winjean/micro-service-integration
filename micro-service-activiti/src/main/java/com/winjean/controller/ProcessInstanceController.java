package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.service.ProcessInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("process/instance")
public class ProcessInstanceController {

    @Autowired
    private ProcessInstanceService processInstanceService;

    /**
     * 流程实例列表
     */
    @PostMapping("list")
    public BaseResponse list(@RequestBody JSONObject param) {
        try{
            return processInstanceService.list(param);
        }catch (Exception e){
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }

    /**
     * 删除流程实例
     */
    @GetMapping("delete/{instanceId}")
    public BaseResponse delete(@PathVariable String instanceId) {
        try{
            return processInstanceService.delete(instanceId);
        }catch (Exception e){
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }


    /**
     * 获取当前流程实例的流程图
     */
    @GetMapping("flowChart/{instanceId}")
    public BaseResponse flowChart(@PathVariable String instanceId) {
        try{
            return processInstanceService.flowChart(instanceId);
        }catch (Exception e){
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }

    /**
     * 获取当前流程实例的历史信息
     * @param instanceId
     * @return
     */
    @GetMapping("history/{instanceId}")
    public BaseResponse history(@PathVariable String instanceId) {
        try{
            return processInstanceService.history(instanceId);
        }catch (Exception e){
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }

    @PostMapping("startByMe")
    public BaseResponse startByMe(@RequestBody JSONObject param) {
        try{
            return processInstanceService.startByMe(param);
        }catch (Exception e){
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }
}
