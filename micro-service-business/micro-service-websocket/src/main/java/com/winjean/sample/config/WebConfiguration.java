package com.winjean.sample.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 解决页面返回的中文乱码。
 * 自定义消息转换器：自定义WebConfiguration继承WebMvcConfigurationSupport类
 * @author ：winjean
 * @date ：Created in 2019/4/15 11:13
 * @description：${description}
 * @modified By：
 * @version: $version$
 */
//@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {

    //1.这个为解决中文乱码
//    @Bean
//    public HttpMessageConverter<String> responseBodyConverter() {
//        StringHttpMessageConverter converter = new StringHttpMessageConverter(Charset.forName("UTF-8"));
//        return converter;
//    }


    //2.1：解决中文乱码后，返回json时可能会出现No converter found for return value of type: xxxx
    //或这个：Could not find acceptable representation
    //解决此问题如下
//    public ObjectMapper getObjectMapper() {
//        return new ObjectMapper();
//    }

    //2.2：解决No converter found for return value of type: xxxx
//    public MappingJackson2HttpMessageConverter messageConverter() {
//        MappingJackson2HttpMessageConverter converter=new MappingJackson2HttpMessageConverter();
//        converter.setObjectMapper(getObjectMapper());
//        return converter;
//    }



//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        super.configureMessageConverters(converters);
//        //解决中文乱码
//        converters.add(responseBodyConverter());
//
//        //解决： 添加解决中文乱码后的配置之后，返回json数据直接报错 500：no convertter for return value of type
//        //或这个：Could not find acceptable representation
//        converters.add(messageConverter());
//    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
                .maxAge(3600)
                .allowCredentials(true);
    }
}
