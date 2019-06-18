package com.winjean.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.winjean.common.BaseResponse;
import com.winjean.service.ModelService;
import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ModelServiceImpl implements ModelService {

    @Autowired
    private RepositoryService repositoryService;

    /**
     * 模型列表
     */
    public BaseResponse list(JSONObject param) {

        JSONObject json = new JSONObject();

        try {
            Integer page = param.getInteger("page") == null ? 0 : param.getInteger("page");
            Integer size = param.getInteger("size") == null ? 10 : param.getInteger("size");
            int start = size * page < 0 ? 0 : size * page;

            List list = new ArrayList<>();
            ModelQuery query = repositoryService.createModelQuery();

            if (StringUtils.isNotEmpty(param.getString("name"))) {
                query.modelNameLike("%" + param.getString("name") + "%");
            }

            if (StringUtils.isNotEmpty(param.getString("key"))) {
                query.modelKey(param.getString("key"));
            }

            List<Model> models = query.orderByCreateTime().desc().listPage(start, size);
            long total = query.count();
            for (Model model : models) {
                Map map = new HashMap<>();
                map.put("id", model.getId());
                map.put("name", model.getName());
                map.put("key", model.getKey());
                map.put("meta", model.getMetaInfo());
                map.put("version", model.getVersion());
                map.put("deploymentId", model.getDeploymentId());
                map.put("createTime", DateFormatUtils.format(model.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                map.put("lastUpdateTime", DateFormatUtils.format(model.getLastUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
                list.add(map);
            }

            json.put("list", list);
            json.put("page", page);
            json.put("total", total);
            json.put("size", size);

        } catch (Exception e) {
            log.error(" query model list failure, query param: {}, message: {}", param, e);

            return BaseResponse.getFailureResponse(e.getMessage());
        }

        return BaseResponse.getSuccessResponse(json);
    }

    /**
     * 创建模型
     */
    public BaseResponse create(JSONObject model) {
        JSONObject json = new JSONObject();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode editorNode = objectMapper.createObjectNode();
            editorNode.put("id", "canvas");
            editorNode.put("resourceId", "canvas");
            ObjectNode stencilSetNode = objectMapper.createObjectNode();
            stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");
            editorNode.set("stencilset", stencilSetNode);
            Model modelData = repositoryService.newModel();

            ObjectNode modelObjectNode = objectMapper.createObjectNode();
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, model.getString("name"));
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
            String description = StringUtils.defaultString(model.getString("description"));
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
            modelData.setMetaInfo(modelObjectNode.toString());
            modelData.setName(model.getString("name"));
            modelData.setKey(StringUtils.defaultString(model.getString("key")));

            repositoryService.saveModel(modelData);
            repositoryService.addModelEditorSource(modelData.getId(), editorNode.toString().getBytes("utf-8"));

            json.put("id", modelData.getId());

            log.info("create model success, model:{}", model);
        } catch (Exception e) {
            log.error("create model failure, message ：", e);
            return BaseResponse.getFailureResponse(e.getMessage());
        }

        return BaseResponse.getSuccessResponse(json);
    }

    /**
     * 根据Model部署流程
     */
    public BaseResponse deploy(String modelId) {
        JSONObject json = new JSONObject();
        try {
            Model modelData = repositoryService.getModel(modelId);
            ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelData.getId()));

            BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
            byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);

            String processName = modelData.getName() + ".bpmn20.xml";
            Deployment deployment = repositoryService.createDeployment().name(modelData.getName()).addString(processName, new String(bpmnBytes, "UTF-8")).deploy();

            json.put("id", deployment.getId());

            log.info("deploy process model success, modelId:{}", modelId);
        } catch (Exception e) {
            log.error("deploy process model failure, modelId: {}, message: {}", modelId, e);

            return BaseResponse.getFailureResponse(e.getMessage());
        }
        return BaseResponse.getSuccessResponse(json);
    }

    @Override
    public Deployment deployFromFile(MultipartFile xmlFile, MultipartFile pngFile, String deploymentName) throws Exception{
        String xmlFileName = xmlFile.getOriginalFilename();
        @Cleanup InputStream xmlInputStream= xmlFile.getInputStream();

        DeploymentBuilder builder = repositoryService.createDeployment()
                .name(deploymentName)
                .addInputStream(xmlFileName, xmlInputStream);

        if(! ObjectUtils.isEmpty(pngFile)){
            String pngFileName = pngFile.getOriginalFilename();
            @Cleanup InputStream pngInputStream= pngFile.getInputStream();
            builder.addInputStream(pngFileName, pngInputStream);
        }
        Deployment deployment = builder.deploy();

        log.info("deploy process model success, modelId:{}", deployment.getId());

        return deployment;
    }

    public BaseResponse delete(String modelId) {

        try {
            repositoryService.deleteModel(modelId);
            log.info("delete process model success, modelId:{}", modelId);
        } catch (Exception e) {
            log.error("delete process model failure, modelId: {},message:{}", modelId, e);
            return BaseResponse.getFailureResponse(e.getMessage());
        }

        return BaseResponse.getSuccessResponse();
    }

    @Override
    public BaseResponse queryListeners(String type) {
        List list= new ArrayList();

        if(type.equalsIgnoreCase("task")){
            list.add(new JSONObject().fluentPut("name","任务监听器").fluentPut("value",""));
        }else if(type.equalsIgnoreCase("event")){
            list.add(new JSONObject().fluentPut("name","事件监听器").fluentPut("value",""));
        }else if(type.equalsIgnoreCase("execution")){
            list.add(new JSONObject().fluentPut("name","执行监听器").fluentPut("value",""));
        }else {
            BaseResponse.getFailureResponse("您输入的类型不正确");
        }

        return BaseResponse.getSuccessResponse(list);
    }

    @Override
    public void download(String modelId, HttpServletResponse response) throws Exception {
        JsonNode editorNode = new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelId));
        BpmnJsonConverter jsonConverter = new BpmnJsonConverter();
        BpmnModel model = jsonConverter.convertToBpmnModel(editorNode);
        String filename = model.getMainProcess().getId() + ".bpmn20.xml";
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);

        @Cleanup ByteArrayInputStream bais = new ByteArrayInputStream(bpmnBytes);
        @Cleanup OutputStream os = response.getOutputStream();

        response.setHeader("Content-Disposition", "attachment;fileName=" + filename);
        StreamUtils.copy(bais, os);
    }

    @Override
    public void convertToModel(String processDefinitionId) throws Exception {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        XMLInputFactory xif = XMLInputFactory.newInstance();
        @Cleanup InputStream is = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinition.getResourceName());
        @Cleanup InputStreamReader isr = new InputStreamReader(is, "UTF-8");
        @Cleanup XMLStreamReader xtr = xif.createXMLStreamReader(isr);
        BpmnModel bpmnModel = new BpmnXMLConverter().convertToBpmnModel(xtr);

        BpmnJsonConverter converter = new BpmnJsonConverter();
        ObjectNode modelNode = converter.convertToJson(bpmnModel);
        Model modelData = repositoryService.newModel();
        modelData.setKey(processDefinition.getKey());
        modelData.setName(processDefinition.getName());
        modelData.setCategory(processDefinition.getDeploymentId());

        ObjectNode modelObjectNode = new ObjectMapper().createObjectNode();
        modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, processDefinition.getName());
        modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);
        modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, processDefinition.getDescription());
        modelData.setMetaInfo(modelObjectNode.toString());

        repositoryService.saveModel(modelData);
        repositoryService.addModelEditorSource(modelData.getId(), modelNode.toString().getBytes("utf-8"));
    }
}
