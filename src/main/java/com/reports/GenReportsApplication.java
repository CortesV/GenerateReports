package com.reports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.reports" })
public class GenReportsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenReportsApplication.class, args);
	}
}
