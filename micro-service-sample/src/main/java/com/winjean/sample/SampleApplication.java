package com.winjean.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;


@SpringCloudApplication
@Slf4j
public class SampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SampleApplication.class, args);
//        log.info(args[1]);

//        String w =System.getProperty("win");
//        log.info(w);
    }
}
