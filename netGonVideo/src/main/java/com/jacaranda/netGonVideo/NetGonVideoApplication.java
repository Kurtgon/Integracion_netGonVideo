package com.jacaranda.netGonVideo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.jacaranda.controller", "com.jacaranda.services"})
public class NetGonVideoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NetGonVideoApplication.class, args);
	}

}
