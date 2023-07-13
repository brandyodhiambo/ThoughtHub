package com.brandyodhiambo.ThoughtHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class ThoughtHubApplication {


	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ThoughtHubApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8089"));
		app.run(args);
	}

}
