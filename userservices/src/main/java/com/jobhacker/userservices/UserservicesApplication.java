package com.jobhacker.userservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity(debug = true)
@EnableJpaRepositories(basePackages = "com.jobhacker.userservices.repository")
public class UserservicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserservicesApplication.class, args);
	}

}
