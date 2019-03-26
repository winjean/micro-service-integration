package com.winjean.zuul.Filter;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.winjean.zuul.route.RouteMapConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 路由zuul
 */
@Slf4j
@Component
public class RouteZuulFilter extends ZuulFilter {

	@Autowired
	private SpringClientFactory clientFactory;

	@Override
	public Object run() {
		// 请求上下文
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		String requestUrl = request.getRequestURL().toString();
		// 打印请求日志
		log.info("请求到达RouteZuulFilter,准备进行路由, 请求URL为:{}", requestUrl);

		// 打印最终请求服务器地址日志
		loggerFinalPath(requestUrl);

		return null;
	}

	/**
	 * 获取上一个过滤器的值，如果值为失败，则不继续执行，如果成功，则继续执行
	 */
	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		return true;//ctx.get("continueRouting") == null ? false : (boolean) ctx.get("continueRouting");
	}

	/**
	 * 优先级
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	/**
	 * 过滤器类型
	 */
	@Override
	public String filterType() {
		return FilterConstants.ROUTE_TYPE;
	}

	/**
	 * 打印出请求最终跳转到的具体机器地址
	 * 
	 * @param url
	 */
	private void loggerFinalPath(String url) {
		try {

			String[] urls = url.split("/",5);

			// 根据请求的url，获取找到该serviceID对应的值
			String urlServiceId = urls[3];
			String subfix = urls[4];

			// 所有路由跳转的路径值
			Map<String, ZuulProperties.ZuulRoute> routesMap = RouteMapConstant.getRoute();
			ZuulProperties.ZuulRoute zuulRoute = routesMap.entrySet().stream().filter( e -> {
					String[] p = e.getValue().getPath().split("/");
					return p[1].equalsIgnoreCase(urlServiceId);
			}).findFirst().get().getValue();

			String serviceId = zuulRoute.getServiceId().toUpperCase();

			ILoadBalancer iLoadBalancer = clientFactory.getLoadBalancer(serviceId);
			Server server = iLoadBalancer.chooseServer("default");
			if (null == server) {
				log.info("zuul invoke failure：{} not exist" , serviceId);
			} else {
				log.info("zuul invoke success :{}/{}", server.getHostPort(), subfix);
			}
		} catch (Exception e) {
			log.error("RouteZuulFilter.loggerFinalPath error -->> :{}", e);
		}

	}
}
