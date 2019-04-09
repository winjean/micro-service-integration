package com.winjean;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients
@EnableHystrixDashboard
public class RoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoleApplication.class, args);
	}

	/*@Bean
	public TomcatServletWebServerFactory getTomcatServletWebServerFactory(){
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory(){

			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint constraint = new SecurityConstraint();
//                INTEGRAL, or CONFIDENTIAL. tomcatapi用于配置访问时加密的，也就是支持https
				constraint.setUserConstraint("CONFIDENTIAL");
				//安全集合
				SecurityCollection collection = new SecurityCollection();
				//配置https的适配url
				collection.addPattern("/");
				constraint.addCollection(collection);
				context.addConstraint(constraint);
			}
		};

		tomcat.addAdditionalTomcatConnectors(httpConnector());

		return tomcat;
	}

	@Bean
	public Connector httpConnector(){
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		//connector监听的端口号
		connector.setPort(7201);
		connector.setSecure(false);
		//监听到http的端口后转向到https的端口
		connector.setRedirectPort(8443);
		return connector;
	}*/
}
