package com.codingChallenge.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception{
        http.csrf((csrf)->csrf.disable())
                .authorizeHttpRequests(authorize->authorize
                		//-----------user ----------------
                        .requestMatchers("/api/user/signup").permitAll()
                        .requestMatchers("/api/patient/add").permitAll()
                        .requestMatchers("/api/doctor/add").permitAll()
                        
                		//-----------appointment ----------------
                        .requestMatchers("/api/patient/doctor/appointment/add").permitAll()
                        .requestMatchers("/api/patient/medicalHistory/add").permitAll()
                        
//---------------------------------
                        .requestMatchers("/api/patient/doctor/getPatients").hasAuthority("DOCTOR")
                        
                        .requestMatchers("/api/patient/medicalHistory/getPatients").permitAll()
                        
                        .requestMatchers("/api/patientlab//getfloor/{patient ID}").permitAll()

                        
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults());
        return http.build();

    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager getAuthManager(AuthenticationConfiguration auth) throws Exception{
        return auth.getAuthenticationManager();
    }



}
