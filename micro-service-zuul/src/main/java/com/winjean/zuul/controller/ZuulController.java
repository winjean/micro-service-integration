package com.winjean.zuul.controller;

import com.winjean.common.BaseResponse;
import com.winjean.zuul.service.ZuulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/25 19:01
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@RestController
@RequestMapping("zuul")
public class ZuulController {

    @Autowired
    private ZuulService zuulService;

    @RequestMapping("queryRoutes")
    public Object queryRoutes(){
        BaseResponse response = BaseResponse.getSuccessResponse();

        Map<String, ZuulProperties.ZuulRoute> zuulRouteMap = zuulService.queryRoutes();
        response.setData(zuulRouteMap);

        return response;
    }

}
