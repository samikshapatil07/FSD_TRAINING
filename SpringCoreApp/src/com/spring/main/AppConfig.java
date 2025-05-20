package com.spring.main;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan
(basePackages = "com.spring") //<-- please scan these packages 

public class AppConfig {  //Central Configuration Class 
	static {
		System.out.println("App Config File Detected and Loaded...");
	}
	
@Bean
	public DataSource getDataSource() {
		String url="jdbc:mysql://localhost:3306/lms_db"; 
		String userDB = "root";
		String passDB = "root@123";
		String driver = "com.mysql.cj.jdbc.Driver";
		DriverManagerDataSource dataSource = new DriverManagerDataSource(url, userDB, passDB);
		dataSource.setDriverClassName(driver);
		
		return dataSource; 
	}
//dB connection
@Bean
	JdbcTemplate getJdbcTemplate(DataSource dataSource){ 
// this jdbctemplate will fire queries on this DS
		return new JdbcTemplate(dataSource); 
	}
	
}