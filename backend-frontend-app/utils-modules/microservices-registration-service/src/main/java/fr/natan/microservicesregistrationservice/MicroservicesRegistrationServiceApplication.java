package fr.natan.microservicesregistrationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicroservicesRegistrationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesRegistrationServiceApplication.class, args);
	}

}
