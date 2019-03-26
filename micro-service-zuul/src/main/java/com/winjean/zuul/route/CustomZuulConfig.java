package com.winjean.zuul.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 动态路由启动配置
 */
@Configuration
public class CustomZuulConfig {

	@Autowired
    ZuulProperties zuulProperties;

	@Autowired
    ServerProperties server;

	@Bean
	public CustomRouteLocator routeLocator() {
		CustomRouteLocator routeLocator = new CustomRouteLocator(this.server.getServlet().getPath(), this.zuulProperties);
		return routeLocator;
	}

}
