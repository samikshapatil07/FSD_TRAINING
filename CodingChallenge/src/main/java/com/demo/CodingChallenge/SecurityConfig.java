package com.demo.CodingChallenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // <- This ensures that this class gets called during every API call
public class SecurityConfig {
	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				.csrf((csrf) -> csrf.disable())
				.authorizeHttpRequests(authorize -> authorize
						
						//------------------- USER -------------------------
						.requestMatchers("/api/user/signup").permitAll()
						.requestMatchers("/api/user/token").authenticated()
						.requestMatchers("/api/user/details").authenticated()
						
            			//------------------- API 1: PATIENT -------------------------
						.requestMatchers("/api/patient/add").permitAll()
						
            			//-------------------API 2:  APPOINTMENT -------------------------

						//.requestMatchers("/api/appointment").permitAll()

            			//-------------------API 3:  get patient with dr ID -------------------------

						.requestMatchers(HttpMethod.GET, "/api/doctor/**").hasRole("DOCTOR")

            			//-------------------API 4:  get patient with medical hostory -------------------------

						  .requestMatchers("/api/patient/{id}").permitAll() 		
						
						.anyRequest().authenticated())
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
				.httpBasic(Customizer.withDefaults()); // <- this activated http basic technique
		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() { // <- Bean saves this object in spring's context
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager getAuthManager(AuthenticationConfiguration auth)
			throws Exception {
		return auth.getAuthenticationManager();
	}
}