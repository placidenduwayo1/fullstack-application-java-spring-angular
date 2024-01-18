package fr.natan.cleanarchitectureprojectservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CleanArchitectureProjectServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleanArchitectureProjectServiceApplication.class, args);
	}

}
