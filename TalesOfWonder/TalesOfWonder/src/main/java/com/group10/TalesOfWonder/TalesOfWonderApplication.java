package com.group10.TalesOfWonder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
public class TalesOfWonderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalesOfWonderApplication.class, args);
	}
}
