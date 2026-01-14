package com.example.StableMatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StableMatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(StableMatchApplication.class, args);
	}

}


// docker run -d --hostname rabbit-host --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management