package com.winjean.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;


//@CrossOrigin(origins = {"http://localhost:7701", "null"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableScheduling
public class WebsocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebsocketApplication.class, args);
    }
}
