package fr.natan.cleanarchitecturecompanyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CleanArchitectureCompanyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CleanArchitectureCompanyServiceApplication.class, args);
	}

}
