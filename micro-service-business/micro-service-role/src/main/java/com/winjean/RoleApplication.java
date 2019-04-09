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
}
