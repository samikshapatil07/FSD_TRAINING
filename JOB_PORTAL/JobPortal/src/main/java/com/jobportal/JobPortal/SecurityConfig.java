package com.jobportal.JobPortal;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		    .csrf((csrf) -> csrf.disable())
			.authorizeHttpRequests(authorize -> authorize
					
					//------------------- FOR EXECUTIVE-------------------------

					/////remove the  ********** thing from api
					//-------------------- FOR USER ----------------------------------
					.requestMatchers("/api/users/signup").permitAll() // allow all to reigister as user
					.requestMatchers("/api/users/{id}").permitAll()// get user by id
					.requestMatchers("/api/user/token").authenticated()
					.requestMatchers("/api/user/details").authenticated()	

					//-------------------- FOR HR -------------------------------------
					.requestMatchers("/api/hr/register").permitAll() // allow all to register as hr
        			.requestMatchers(HttpMethod.GET,"/api/hr/**").permitAll() // all get(GetById,GetAll) operations for hr are permit all
        			.requestMatchers(HttpMethod.PUT,"/api/hr/{hrId}").hasAuthority("HR") // only hr can update hr details
        			
					//-------------------- FOR JOB SEEKER -------------------------------------
					.requestMatchers("/api/jobseekers/register").permitAll() // register job_seeker (anyone)
        			.requestMatchers(HttpMethod.GET,"/api/jobseekers/**").permitAll() // all get(GetById,GetAll) operations for js are permit all
        			.requestMatchers(HttpMethod.PUT,"/api/jobseekers/{jobSeekerId}").hasAuthority("JOB_SEEKER") // only jobseekers can update  details
					
        			// -------------------- FOR JOB POSTING  -------------------------------------
        			.requestMatchers(HttpMethod.POST, "/api/jobs/**").hasAuthority("HR") // only hr can post jobs
        			.requestMatchers(HttpMethod.PUT, "/api/jobs/**").hasAuthority("HR") //only hr can update teh jobs
        			.requestMatchers(HttpMethod.DELETE, "/api/jobs/**").hasAuthority("HR") //only hr can delete the jobs
        			.requestMatchers(HttpMethod.GET, "/api/jobs/**").permitAll() // all can view the jobs that hr has posted
        			
        			// -------------------- FOR  APPLICATIONS  -------------------------------------
                    .requestMatchers(HttpMethod.POST, "/api/applications/{seekerId}/{jobId}").hasAuthority("JOB_SEEKER") //only job seeker can apply for job
                    .requestMatchers(HttpMethod.PUT, "/api/applications/**").hasAuthority("JOB_SEEKER") // only job seeker can update the application
                    .requestMatchers(HttpMethod.GET, "/api/applications/job/**").hasAuthority("HR") //view  applications by job ID
                    .requestMatchers(HttpMethod.GET, "/api/applications").hasAuthority("HR") //see all applications
                    .requestMatchers(HttpMethod.GET, "/api/applications/jobseeker/**").hasAuthority("JOB_SEEKER") //view appliction by js ID
                    .requestMatchers(HttpMethod.DELETE, "/api/applications/**").hasAuthority("JOB_SEEKER") //only js can delete/withdraw application
                    .requestMatchers(HttpMethod.PATCH, "/api/applications/**").hasAuthority("HR") // only hr can update the application as 
                     //REJECTED,SHORTLISTED,INTERVIEW_SCHEDULED,INTERVIEW_COMPLETED,INTERVIEW_COMPLETED_OFFERED,INTERVIEW_COMPLETED_REJECTED,
                    
        			// -------------------- FOR  SEEKER ACTIVITY  -------------------------------------
                    .requestMatchers(HttpMethod.POST, "/api/seeker-activity").hasAuthority("JOB_SEEKER") //only job seeker can view their activity
                    .requestMatchers(HttpMethod.GET, "/api/seeker-activity").permitAll() //here the access is only for admin but for now i have done it permit all>>>>---i will work on it later
                    .requestMatchers(HttpMethod.GET, "/api/seeker-activity/{jobSeekerId}").hasAnyAuthority("JOB_SEEKER") //here the access is even for admin but for job seeker>>>>---i will work on it later
                    .requestMatchers(HttpMethod.DELETE, "/api/seeker-activity/{activityId}").permitAll() //here the access is only for admin but for now i have done it permit all>>>>---i will work on it later

        			// -------------------- FOR APPLICATION UPDATE ----------------------------------
                    .requestMatchers(HttpMethod.POST, "/api/updates").hasAuthority("JOB_SEEKER") // Only js can post updates
                    .requestMatchers(HttpMethod.GET, "/api/updates/application/**").hasAnyAuthority("JOB_SEEKER", "HR") // View updates for specific application
                    .requestMatchers(HttpMethod.GET, "/api/updates/jobseeker/**").hasAuthority("JOB_SEEKER") // only job seeker can see the updates made by them

                 // -------------------- FOR INTERVIEW ----------------------------------
                    .requestMatchers(HttpMethod.POST, "/api/interviews/application/{appId}").hasAuthority("HR") // HR schedules
                    .requestMatchers(HttpMethod.PUT, "/api/interviews/{appId}").hasAuthority("HR") // HR updates
                    .requestMatchers(HttpMethod.DELETE, "/api/interviews/**").hasAuthority("HR") // HR deletes
                    .requestMatchers(HttpMethod.GET, "/api/interviews/application/**").hasAnyAuthority("HR", "JOB_SEEKER") // View interviews for an application
                    .requestMatchers(HttpMethod.GET, "/api/interviews/**").hasAnyAuthority("HR", "JOB_SEEKER") // View specific interview

					.anyRequest().authenticated()  
			)
	
	 .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) 
	 .httpBasic(Customizer.withDefaults()); //<- this activated http basic technique
	return http.build();
}

	@Bean
	 PasswordEncoder passwordEncoder() { //<--- bean saves this obj. in springs context
		return new BCryptPasswordEncoder();
	}
	
	// Bean to provide AuthenticationManager from Spring Security configuration
	@Bean
	AuthenticationManager getAuthManager(AuthenticationConfiguration auth) 
			throws Exception {
		  return auth.getAuthenticationManager();
	 }

}

