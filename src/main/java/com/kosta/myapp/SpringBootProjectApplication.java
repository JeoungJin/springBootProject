package com.kosta.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@EntityScan("com.kosta")   
//@ComponentScan("com.kosta")  //@Component, @Service, @Controller, @Repository(X)
//@EnableJpaRepositories("com.kosta.myapp.repository")
public class SpringBootProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProjectApplication.class, args);
	}

}
