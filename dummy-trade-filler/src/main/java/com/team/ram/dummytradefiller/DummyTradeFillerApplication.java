package com.team.ram.dummytradefiller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DummyTradeFillerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DummyTradeFillerApplication.class, args);
	}

}
