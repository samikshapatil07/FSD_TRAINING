package com.springboot.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
/*telling spring to exclude security 
 * dependancy for now as i have not configured it...
 * we will activate it letter on
 * 
 * ctrl + shift + o = auto import*/
@EnableTransactionManagement
public class LmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsApplication.class, args);
	}

}