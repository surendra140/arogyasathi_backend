package com.arogyasathi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ArogyasathiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArogyasathiApplication.class, args);
	}

}
