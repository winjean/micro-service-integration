package com.winjean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
//@EnableHystrixDashboard
public class RoleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RoleApplication.class, args);
	}
}
