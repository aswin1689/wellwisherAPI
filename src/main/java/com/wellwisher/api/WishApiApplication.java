package com.wellwisher.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WishApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WishApiApplication.class, args);
	}

}
