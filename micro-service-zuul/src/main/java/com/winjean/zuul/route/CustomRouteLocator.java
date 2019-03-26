package com.winjean.zuul.route;

import com.github.pagehelper.Page;
import com.winjean.zuul.mapper.ZuulRouteMapper;
import com.winjean.zuul.model.response.ResponseZuulRouteQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class CustomRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

	@Resource
	private ZuulRouteMapper zuulRouteMapper;

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
		// 从application.yml中加载路由信息
		Map<String, ZuulRoute> routesMap = super.locateRoutes();
		Page<ResponseZuulRouteQuery> zuulRoutes = zuulRouteMapper.query();
		List<ResponseZuulRouteQuery> list = zuulRoutes.getResult();
		list.forEach(zr -> {
			ZuulRoute zuulRoute = new ZuulRoute();
			zuulRoute.setId(zr.getId());
			zuulRoute.setPath(zr.getPath());
			zuulRoute.setServiceId(zr.getServiceId());
			zuulRoute.setUrl(zr.getUrl());
			zuulRoute.setStripPrefix(zr.isStripPrefix());
			zuulRoute.setRetryable(zr.getRetryable());

			routesMap.put(zuulRoute.getPath(),zuulRoute);
		});

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
