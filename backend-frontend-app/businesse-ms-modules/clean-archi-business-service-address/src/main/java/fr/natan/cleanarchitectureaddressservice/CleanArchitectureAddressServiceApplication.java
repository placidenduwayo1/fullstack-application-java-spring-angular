package fr.natan.cleanarchitectureaddressservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CleanArchitectureAddressServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleanArchitectureAddressServiceApplication.class, args);
	}

}
