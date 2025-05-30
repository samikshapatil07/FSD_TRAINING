package com.jobportal.JobPortal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		    .csrf((csrf) -> csrf.disable())
			.authorizeHttpRequests(authorize -> authorize
					//-------------------- FOR USER ----------------------------------
					.requestMatchers("/api/users/signup").permitAll() // reigister user
					.requestMatchers("/api/users/{id}").permitAll()// get user by id
					
					//-------------------- FOR HR -------------------------------------
					.requestMatchers("/api/hr/register").permitAll() // register hr
        			.requestMatchers("/api/hr/{hrId}").hasAuthority("HR") // only hr can update hr details
					
					//-------------------- FOR JOB SEEKER -------------------------------------
					.requestMatchers("/api/jobseekers/register").permitAll() // register job_seeker
        			.requestMatchers("/api/jobseekers/{jobSeekerId}").hasAuthority("JOB_SEEKER") // only jobseekers can update jobseekers details
					
        			// -------------------- FOR JOB POSTING  -------------------------------------
        			.requestMatchers(HttpMethod.POST, "/api/jobs/**").hasAuthority("HR")
        			.requestMatchers(HttpMethod.PUT, "/api/jobs/**").hasAuthority("HR")
        			.requestMatchers(HttpMethod.DELETE, "/api/jobs/**").hasAuthority("HR")
        			.requestMatchers(HttpMethod.GET, "/api/jobs/**").permitAll()


					


//					.requestMatchers("/api/learner/add").permitAll() //for earner
//					.requestMatchers("/api/learner/get-one/{id}").hasAuthority("LEARNER")
//					.requestMatchers("/api/course/add").hasAnyAuthority("AUTHOR","EXECUTIVE")
					.anyRequest().authenticated()  
			)
		 .httpBasic(Customizer.withDefaults()); //<- this activated http basic technique
		return http.build();
	}
	
	@Bean
	 PasswordEncoder passwordEncoder() { //<--- bean saves this obj. in springs context
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager getAuthManager(AuthenticationConfiguration auth) 
			throws Exception {
		  return auth.getAuthenticationManager();
	 }

}

