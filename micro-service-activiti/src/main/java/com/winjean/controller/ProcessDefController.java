package com.winjean.controller;

import com.alibaba.fastjson.JSONObject;
import com.winjean.common.BaseResponse;
import com.winjean.service.ProcessDefService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("process/definition")
public class ProcessDefController {

    @Autowired
    private ProcessDefService processDefService;

    /**
     * 流程定义列表
     */
    @PostMapping("list")
    public BaseResponse list(@RequestBody JSONObject param) {
        return processDefService.list(param);
    }

    @GetMapping("delete/{deploymentId}")
    public BaseResponse delete(@PathVariable String deploymentId) {

        try{
            return processDefService.delete(deploymentId);
        }catch (Exception e){
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }

    @RequestMapping({"start/{procDefKey}","start/{procDefKey}/{businessKey}"})
    public BaseResponse start(@PathVariable String procDefKey,
                              @PathVariable(required = false) String businessKey,
                              @RequestBody(required = false) Map<String,Object> map) {
        try{
            return processDefService.start(procDefKey, businessKey, map);
        }catch (Exception e){
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }

    /**
     * 获取当前流程实例的流程图
     */
    @GetMapping("flowChart/{procDefId}")
    public BaseResponse flowChart(@PathVariable String procDefId) {

        try{
            return processDefService.flowChart(procDefId);
        }catch (Exception e){
            return BaseResponse.getFailureResponse(e.getMessage());
        }
    }

    /**
     * 下载流程定义文件
     * @param processDefinitionId
     * @param type xml or png
     * @param response
     * @throws Exception
     */
    @GetMapping("download/{processDefinitionId}/{type}")
    public void download(@PathVariable("processDefinitionId") String processDefinitionId, @PathVariable("type") String type, HttpServletResponse response) throws Exception{
        processDefService.download(processDefinitionId,type, response);
    }
}
