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
					.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() //preflight
					
					//-------------------- FOR USER ----------------------------------
					.requestMatchers("/api/user/signup").permitAll() // allow all to register as user
					.requestMatchers("/api/user/token").permitAll()
					.requestMatchers("/api/user/details").authenticated() //login
					
					.requestMatchers("/api/user/details/{id}").hasAuthority("EXECUTIVE")	//only executive can get  users by id

					//-------------------- FOR HR -------------------------------------
					.requestMatchers("/api/hr/register").permitAll() // allow all to register as hr
        			.requestMatchers("/api/hr/update/me").hasAuthority("HR") // update hr(logged in hr only)
        			.requestMatchers("/api/hr/username/{username}").hasAuthority("HR") // get hr by username


        			.requestMatchers("/api/hr/Id/{hrId}").hasAuthority("EXECUTIVE") // get hr by hr id
        			.requestMatchers("/api/hr/delete/{hrId}").hasAuthority("EXECUTIVE") // delete hr by id
        			.requestMatchers("/api/hr/all").hasAuthority("EXECUTIVE") //get all hr's 

        			
					//-------------------- FOR JOB SEEKER -------------------------------------
					.requestMatchers("/api/jobseeker/register").permitAll() // register job_seeker (anyone)
        			.requestMatchers("/api/jobseeker/username/{username}").hasAuthority("JOB_SEEKER") // get js 
        			.requestMatchers("/api/jobseeker/update/me").hasAuthority("JOB_SEEKER") // only jobseekers can update  details
        			
        			.requestMatchers("/api/jobseeker/delete/{jobSeekerId}").hasAuthority("EXECUTIVE") // delete js by id
        			.requestMatchers("/api/jobseeker/all").hasAuthority("EXECUTIVE") // get all js

					
        			// -------------------- FOR JOB POSTING  -------------------------------------
        			.requestMatchers( "/api/jobs/add").hasAuthority("HR") // only hr can post jobs
        			.requestMatchers("/api/jobs/all").permitAll() // all can view the jobs that hr has posted
        			.requestMatchers( "/api/jobs/update/{JobId}").hasAuthority("HR") //only hr can update the jobs by job id
        			.requestMatchers( "/api/jobs/delete/{id}").hasAuthority("HR") //only hr can delete the jobs
        			.requestMatchers("/api/jobs/search").permitAll() // search jobs by title, location, and company name
        			.requestMatchers( "/api/jobs/by-hr").hasAuthority("HR") //get jobs posted by logged in hr

        			

        			// -------------------- FOR  APPLICATIONS  -------------------------------------
                    .requestMatchers("/api/applications/apply/{jobId}").hasAuthority("JOB_SEEKER") //only job seeker can apply for job            
                    .requestMatchers("/api/applications/jobId/{JobID}").hasAuthority("HR") //view  applications by job ID
                    .requestMatchers("/api/applications/for-hr").hasAuthority("HR") //view appliction by js ID
                    .requestMatchers("/api/applications/for-js").hasAuthority("JOB_SEEKER") //get application of  logged in js

                    
                    
                    .requestMatchers("/api/applications/{id}").hasAnyAuthority("HR","JOB_SEEKER") 
                    .requestMatchers("/api/applications/get-all").hasAuthority("HR") 
                    .requestMatchers("/api/applications/delete/{appId}").hasAuthority("JOB_SEEKER") 
                    .requestMatchers("/api/applications/status/{applicationId}").hasAuthority("JOB_SEEKER") 
                    .requestMatchers("/api/applications/update/{appId}").hasAuthority("JOB_SEEKER") 
                    .requestMatchers("/api/applications/update/status/{appId}").hasAuthority("HR") 
                    .requestMatchers("/api/applications/my-applications").hasAuthority("HR") 
	
                    // -------------------- FOR INTERVIEW ----------------------------------
                    .requestMatchers("/api/interviews/schedule/application/{appId}").hasAuthority("HR") // HR schedules
                    .requestMatchers( "/api/interviews/applicationId/{appID}").hasAnyAuthority("HR", "JOB_SEEKER") // View interviews for an application
                    .requestMatchers( "/api/interviews/update-for/interview/{interviewId}").hasAuthority("HR") // HR update
                    .requestMatchers("/api/interviews/delete/{interviewId}").hasAuthority("HR") // HR deletes
                     .requestMatchers( "/api/interviews/update/outcome/interviewId/{interviewId}").hasAuthority("HR") // HR updates
                     .requestMatchers("/api/interviews/by-hr").hasAuthority("HR") // HR deletes
                     .requestMatchers("/api/interviews/for-js").hasAuthority("JOB_SEEKER") // HR deletes
                     
                     
        			// -------------------- FOR APPLICATION UPDATE ----------------------------------
                    .requestMatchers("/api/updates/application/{appId}").hasAuthority("HR") // View updates for specific application


                  // -------------------- FOR  SEEKER ACTIVITY  -------------------------------------
                     .requestMatchers( "/api/seeker-activities").hasAuthority("EXECUTIVE") 
                     .requestMatchers("/api/seeker-activities/jobseeker/{jobSeekerId}").hasAuthority("EXECUTIVE")  



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

