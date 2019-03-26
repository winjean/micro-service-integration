package com.winjean.zuul.service;

import com.github.pagehelper.Page;
import com.winjean.zuul.model.request.RequestZuulRouteInsert;
import com.winjean.zuul.model.request.RequestZuulRouteQueryPage;
import com.winjean.zuul.model.response.ResponseZuulRouteQuery;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;

import java.util.Map;

/**
 * @author ：winjean
 * @date ：Created in 2019/3/25 18:36
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
public interface ZuulService {

    Map<String, ZuulRoute> queryRoutes();

    Page<ResponseZuulRouteQuery> queryRoutes(RequestZuulRouteQueryPage request);

    void addRoute(RequestZuulRouteInsert request);

    void refreshRoute();
}
