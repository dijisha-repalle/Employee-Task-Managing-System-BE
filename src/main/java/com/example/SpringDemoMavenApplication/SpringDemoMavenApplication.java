package com.example.SpringDemoMavenApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringDemoMavenApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoMavenApplication.class, args);
	}

}
