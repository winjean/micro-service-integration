package com.winjean.zuul.service.impl;

import com.winjean.zuul.route.CustomRouteLocator;
import com.winjean.zuul.service.ZuulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.stereotype.Service;

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

    @Override
    public Map<String, ZuulRoute> queryRoutes() {

        CustomRouteLocator locator = new CustomRouteLocator(server.getServlet().getServletPrefix(),zuulProperties);

        return locator.locateRoutes();
    }
}
