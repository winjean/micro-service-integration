package com.winjean.zuul.service.impl;

import com.github.pagehelper.Page;
import com.winjean.utils.BeanUtils;
import com.winjean.zuul.mapper.ZuulRouteMapper;
import com.winjean.zuul.model.request.RequestZuulRouteInsert;
import com.winjean.zuul.model.request.RequestZuulRouteQueryPage;
import com.winjean.zuul.model.response.ResponseZuulRouteQuery;
import com.winjean.zuul.route.RefreshRoute;
import com.winjean.zuul.route.RouteMapConstant;
import com.winjean.zuul.service.ZuulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
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

    @Resource
    private ZuulRouteMapper mapper;

    @Autowired
    private RefreshRoute refreshRoute;

    @Override
    public void addRoute(RequestZuulRouteInsert request) {
        BeanUtils.appendBeanInfo(request,"winjean");
        mapper.insert(request);
    }

    @Override
    public Map<String, ZuulRoute> queryRoutes() {

        return RouteMapConstant.getRoute();
    }

    @Override
    public Page<ResponseZuulRouteQuery> queryRoutes(RequestZuulRouteQueryPage request) {
        return mapper.query();
    }

    @Override
    public void refreshRoute() {
        RouteMapConstant.clearRoute();

		Page<ResponseZuulRouteQuery> zuulRoutes = mapper.query();
		List<ResponseZuulRouteQuery> list = zuulRoutes.getResult();
		list.forEach(zr -> {
			ZuulRoute zuulRoute = new ZuulRoute();
			zuulRoute.setId(zr.getId());
			zuulRoute.setPath(zr.getPath());
			zuulRoute.setServiceId(zr.getServiceId());
			zuulRoute.setUrl(zr.getUrl());
			zuulRoute.setStripPrefix(zr.isStripPrefix());
			zuulRoute.setRetryable(zr.getRetryable());

			RouteMapConstant.appendRoute(zuulRoute);
		});
        refreshRoute.refresh();
    }
}
