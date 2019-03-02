package com.winjean.config.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *
 *
 * 创建时间：2018/10/14 9:56
 * 修改人：Administrator
 * 修改时间：2018/10/14 9:56
 * 修改备注：
 * 版权所有权：
 *
 *
 */
@SpringBootApplication
@EnableConfigServer
@EnableEurekaClient
public class ServiceConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceConfigApplication.class,args);
    }
}
