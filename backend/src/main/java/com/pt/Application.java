package com.pt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication app = new  SpringApplication(Application.class);
		app.run(args);
	}
	

}
