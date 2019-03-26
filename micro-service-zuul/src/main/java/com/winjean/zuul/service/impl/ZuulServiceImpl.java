package com.winjean.zuul.service.impl;

import com.winjean.utils.BeanUtils;
import com.winjean.zuul.mapper.ZuulRouteMapper;
import com.winjean.zuul.model.request.RequestZuulRouteInsert;
import com.winjean.zuul.route.CustomRouteLocator;
import com.winjean.zuul.service.ZuulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/25 18:36
 * @description：${description}
 * @modified By：
 * @version: $version$
 */

@Service
public class ZuulServiceImpl implements ZuulService {

    @Autowired
    private ZuulProperties zuulProperties;

    @Autowired
    private ServerProperties server;

    @Resource
    private ZuulRouteMapper mapper;

    @Override
    public void addRoute(RequestZuulRouteInsert request) {
        BeanUtils.appendBeanInfo(request,"winjean");
        mapper.insert(request);
    }

    @Override
    public Map<String, ZuulRoute> queryRoutes() {

        CustomRouteLocator locator = new CustomRouteLocator(server.getServlet().getPath(),zuulProperties);

        return locator.locateRoutes();
    }
}
