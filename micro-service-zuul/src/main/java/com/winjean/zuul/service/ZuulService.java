package com.winjean.zuul.service;

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
}
