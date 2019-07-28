package com.practice.ms.ConfigServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author Ashok
 * 
 * This is spring boot application server. In distributed system this is used to store
 * configuration properties of various applications like currency exchange service, limit service.
 * It also stores configuration info of various environment.
 *
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigServerApplication {

	public static void main(String[] args) {
		System.out.println(System.getProperty("user.home"));
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
