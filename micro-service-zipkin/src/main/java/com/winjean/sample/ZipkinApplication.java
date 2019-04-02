package com.winjean.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;


@SpringCloudApplication
public class ZipkinApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZipkinApplication.class, args);
    }
}
