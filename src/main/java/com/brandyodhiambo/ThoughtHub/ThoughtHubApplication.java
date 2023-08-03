package com.brandyodhiambo.ThoughtHub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;
import java.util.Date;

@SpringBootApplication
public class ThoughtHubApplication {


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ThoughtHubApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8089"));
		app.run(args);
	}

	@Bean
	CommandLineRunner runner () {
		return args -> {
			System.out.println("THOUGHT HUB APPLICATION STARTED SUCCESSFULLY AT PORT: 8089 ON " + new Date());
		};
	}

}
