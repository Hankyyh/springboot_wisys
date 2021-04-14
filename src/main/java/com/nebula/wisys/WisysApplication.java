package com.nebula.wisys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.nebula.wisys.controller"})
public class WisysApplication {

	public static void main(String[] args) {
		SpringApplication.run(WisysApplication.class, args);
	}

}
