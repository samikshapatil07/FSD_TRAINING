package com.hibernate.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context 
					= new AnnotationConfigApplicationContext(AppConfig.class);
	}

}
