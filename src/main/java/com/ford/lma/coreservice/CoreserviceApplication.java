package com.ford.lma.coreservice;

import com.ford.cloudnative.annotations.EnableFordSecurityTools;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableFordSecurityTools
public class CoreserviceApplication {
	
	private static Logger logger = LogManager.getLogger(CoreserviceApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CoreserviceApplication.class, args);
	}

}
