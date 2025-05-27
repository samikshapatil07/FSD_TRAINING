package com.hibernate.main;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@ComponentScan(basePackages = "com.hibernate.main")
@EnableTransactionManagement
public class AppConfig {
	
	static {
		System.out.println("AppConfig called....");
	}
	@Bean
	public DataSource getDataSource() {
		String url="jdbc:mysql://localhost:3306/lms_hibernate_db";
		String userDB = "root";
		String passDB = "root@123";
		String driver = "com.mysql.cj.jdbc.Driver";
		DriverManagerDataSource dataSource = new DriverManagerDataSource(url, userDB, passDB);
		dataSource.setDriverClassName(driver);
		
		return dataSource;
	}
	@Bean
	public LocalContainerEntityManagerFactoryBean getEntityManagerFactory(){
		LocalContainerEntityManagerFactoryBean emFactory
						= new LocalContainerEntityManagerFactoryBean();
		emFactory.setDataSource(getDataSource());
		emFactory.setPackagesToScan("com.hibernate.main.model");
		
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		emFactory.setJpaVendorAdapter(vendorAdapter);
		emFactory.setJpaProperties(getProperties());
		return emFactory;
	}
	
	
	public Properties getProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update"); //create, create-drop,update,none
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		return properties;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory em) {
		JpaTransactionManager jpaTransactionManager =  new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(em);
		return jpaTransactionManager;
	}
}
 

