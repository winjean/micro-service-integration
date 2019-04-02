package com.winjean.config;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringCloudApplication
@EnableConfigServer
public class ServiceConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceConfigApplication.class,args);
    }
}
