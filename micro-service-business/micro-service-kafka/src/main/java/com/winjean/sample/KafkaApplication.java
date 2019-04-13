package com.winjean.sample;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;


@SpringCloudApplication
public class KafkaApplication {
    public static void main(String[] args) {
        SpringApplication.run(KafkaApplication.class, args);
    }
}
