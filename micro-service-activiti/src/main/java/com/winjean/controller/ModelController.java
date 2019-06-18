//package com.winjean.controller.model;
//
//import com.alibaba.fastjson.JSONObject;
//import com.winjean.common.BaseResponse;
//import com.winjean.service.ModelService_old;
//import lombok.extern.slf4j.Slf4j;
//import org.activiti.engine.repository.Deployment;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author ：winjean
// * @date ：Created in 2019/2/22 14:52
// * @description：${description}
// * @modified By：
// * @version: $version$
// */
//@RestController
//@Slf4j
//@RequestMapping("process/model")
//public class ModelController {
//
//    @Autowired
//    private ModelService_old modelService;
//
//    /**
//     * 模型列表
//     */
//    @PostMapping("list")
//    public BaseResponse modelList(@RequestBody JSONObject param) {
//        return modelService.list(param);
//    }
//
//    /**
//     * 创建模型
//     */
//    @PostMapping("create")
//    public BaseResponse create(@RequestBody CreateModel model) {
//        return modelService.create(model);
//    }
//
//    /**
//     * 根据Model部署流程
//     */
//    @GetMapping(value = "/deploy/{modelId}")
//    public BaseResponse deploy(@PathVariable("modelId") String modelId) {
//        return modelService.deploy(modelId);
//    }
//
//    /**
//     * delete process model
//     * @param modelId
//     * @return
//     */
//    @GetMapping(value = "/delete/{modelId}")
//    public BaseResponse delete(@PathVariable("modelId") String modelId) {
//        return modelService.delete(modelId);
//    }
//
//    @GetMapping(value = "/listener/list/{type}")
//    public BaseResponse queryListeners(@PathVariable("type") String type) {
//        return modelService.queryListeners(type);
//    }
//
//    /**
//     * 根据流程定义文件部署流程
//     * @param xmlFile
//     * @param pngFile
//     * @param deploymentName
//     * @return
//     * @throws Exception
//     */
//    @PostMapping("deployFromFile")
//    public BaseResponse deployFromFile(@RequestParam("xmlFileName") MultipartFile xmlFile, @RequestParam(value = "pngFileName",required = false) MultipartFile pngFile,
//                                       @RequestParam(value = "deploymentName", required = false,defaultValue = "deploymentName") String deploymentName) throws Exception{
//        Deployment deployment = modelService.deployFromFile(xmlFile, pngFile, deploymentName);
//        return BaseResponse.getSuccessResponse(deployment);
//    }
//
//    /**
//     * 下载流程定义文件
//     * @param modelId
//     * @param response
//     * @throws Exception
//     */
//    @GetMapping("download/{modelId}")
//    public void download(@PathVariable("modelId") String modelId, HttpServletResponse response) throws Exception{
//        modelService.download(modelId, response);
//    }
//
//    /**
//     * 将流程定义转换成流程模型
//     * @param processDefinitionId
//     * @return
//     * @throws Exception
//     */
//    @GetMapping("convertToModel/{processDefinitionId}")
//    public BaseResponse convertToModel(@PathVariable("processDefinitionId") String processDefinitionId) throws Exception{
//        modelService.convertToModel(processDefinitionId);
//        return BaseResponse.getSuccessResponse();
//    }
//
//}
//
//
