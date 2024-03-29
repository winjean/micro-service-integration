package com.winjean.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;

/**
 * @author ：winjean
 * @date ：Created in 2019/4/9 15:29
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
//@Configuration
public class Http2HttpsConfig {

    @Value("${server.port}")
    private int port;

//    @Bean
//    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> webServerFactoryCustomizer(){

//        new TomcatWebServerFactoryCustomizer()
		/*TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory(){

			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint constraint = new SecurityConstraint();
//                INTEGRAL, or CONFIDENTIAL. tomcatapi用于配置访问时加密的，也就是支持https
				constraint.setUserConstraint("CONFIDENTIAL");
				//安全集合
				SecurityCollection collection = new SecurityCollection();
				//配置https的适配url
				collection.addPattern("/*");
				constraint.addCollection(collection);
				context.addConstraint(constraint);
			}
		};*/

//		tomcat.addAdditionalTomcatConnectors(httpConnector());

//        return (factory) -> {
//            factory.setPort(7021);
//            factory.addAdditionalTomcatConnectors(httpConnector());
//        };

//		return tomcat;
//        return null;
//    }

//    @Bean
    public TomcatServletWebServerFactory tomcatServletWebServerFactory(){
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory(){

            @Override
            protected void postProcessContext(Context context) {
                //安全集合
                SecurityCollection collection = new SecurityCollection();
                //配置https的适配url
                collection.addPattern("/*");

                SecurityConstraint constraint = new SecurityConstraint();
//                INTEGRAL, or CONFIDENTIAL. tomcatapi用于配置访问时加密的，也就是支持https
                constraint.setUserConstraint("CONFIDENTIAL");
                constraint.addCollection(collection);
                context.addConstraint(constraint);
            }
        };

		tomcat.addAdditionalTomcatConnectors(httpConnector());

        return tomcat;
    }

//    @Bean
    public Connector httpConnector(){
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        //connector监听的端口号
        connector.setPort(7211);
        connector.setSecure(false);
        //监听到http的端口后转向到https的端口
        connector.setRedirectPort(port);
        return connector;
    }
}
