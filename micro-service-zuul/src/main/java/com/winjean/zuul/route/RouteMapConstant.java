package com.winjean.zuul.route;

import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;

import java.util.LinkedHashMap;
import java.util.Map;

public class RouteMapConstant {

	public static Map<String, ZuulRoute> route = new LinkedHashMap<>();

	public static Map<String, ZuulRoute> getRoute() {
		return route;
	}

	public static void appendRoute(ZuulRoute zuulRoute) {
		route.put(zuulRoute.getPath(), zuulRoute);
	}

	public static void setRoute(Map<String, ZuulRoute> route) {
		RouteMapConstant.route = route;
	}

	public static void clearRoute() {
		RouteMapConstant.route.clear();
	}
}
