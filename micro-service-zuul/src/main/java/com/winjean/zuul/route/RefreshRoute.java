package com.winjean.zuul.route;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.RoutesRefreshedEvent;
import org.springframework.cloud.netflix.zuul.filters.RouteLocator;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class RefreshRoute {

	@Autowired
    ApplicationEventPublisher publisher;

	@Autowired
    RouteLocator routeLocator;
	
	public void refresh() {
		RoutesRefreshedEvent routesRefreshedEvent = new RoutesRefreshedEvent(routeLocator);
		publisher.publishEvent(routesRefreshedEvent);
	}

}
