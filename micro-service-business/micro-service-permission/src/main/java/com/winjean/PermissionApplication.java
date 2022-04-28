package com.winjean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author winjean
 */

@SpringBootApplication
@EnableDiscoveryClient
public class PermissionApplication {

	public static void main(String[] args) {
		SpringApplication.run(PermissionApplication.class, args);
	}
}
