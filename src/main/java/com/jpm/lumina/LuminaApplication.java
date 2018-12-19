package com.jpm.lumina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={"com.jpm.lumina.controller","com.jpm.lumina.config","com.jpm.lumina.service"})
public class LuminaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuminaApplication.class, args);
	}

}

