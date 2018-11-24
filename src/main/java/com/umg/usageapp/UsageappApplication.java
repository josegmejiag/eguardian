package com.umg.usageapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class UsageappApplication extends SpringBootServletInitializer {

	
	   @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.sources(UsageappApplication.class);
	    }
	
	
	public static void main(String[] args) {
		SpringApplication.run(UsageappApplication.class, args);
	}
}
