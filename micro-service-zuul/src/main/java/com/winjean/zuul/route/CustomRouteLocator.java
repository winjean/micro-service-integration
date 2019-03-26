package com.winjean.zuul.route;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class CustomRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    @Autowired
    private ZuulProperties zuulProperties;

	public Map<String, ZuulRoute> getRoutesMap(){

        Map<String, ZuulRoute> routesMap = super.getRoutesMap();

	    return routesMap;
    }

	public CustomRouteLocator(String servletPath, ZuulProperties properties) {
		super(servletPath, properties);
		this.zuulProperties = properties;
	}

	@Override
	public void refresh() {
		doRefresh();
	}

	@Override
	public Map<String, ZuulRoute> locateRoutes() {
		Map<String, ZuulRoute> routesMap = RouteMapConstant.getRoute();
		// 从application.yml中加载路由信息
		Map<String, ZuulRoute> routeMap = super.locateRoutes();

		if (MapUtils.isNotEmpty(routeMap)) {
			routesMap.putAll(routeMap);
		}

		// 优化一下配置
		LinkedHashMap<String, ZuulRoute> values = new LinkedHashMap<>();
		for (Map.Entry<String, ZuulRoute> entry : routesMap.entrySet()) {
			String path = entry.getKey();
			// Prepend with slash if not already present.
			if (!path.startsWith("/")) {
				path = "/" + path;
			}
			if (StringUtils.hasText(this.zuulProperties.getPrefix())) {
				path = this.zuulProperties.getPrefix() + path;
				if (!path.startsWith("/")) {
					path = "/" + path;
				}
			}
			values.put(path, entry.getValue());
		}
		return values;
	}

}
