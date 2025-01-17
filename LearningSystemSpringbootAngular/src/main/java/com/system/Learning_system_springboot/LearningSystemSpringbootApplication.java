package com.system.Learning_system_springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LearningSystemSpringbootApplication {
	public static void main(String[] args) {
		SpringApplication.run(LearningSystemSpringbootApplication.class, args);
	}
}
