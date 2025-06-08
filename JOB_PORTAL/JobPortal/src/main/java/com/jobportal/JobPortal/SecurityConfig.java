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
	 //// this class need  an update...
	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception { //////////////// i have to update the aips by permition from application ...remove **** thing
		http
		    .csrf((csrf) -> csrf.disable())
			.authorizeHttpRequests(authorize -> authorize
					
					//------------------- FOR EXECUTIVE-------------------------

					//-------------------- FOR USER ----------------------------------
					.requestMatchers("/api/users/signup").permitAll() // allow all to register as user
					.requestMatchers("/api/users/{id}").permitAll()// get user by id
					.requestMatchers("/api/user/token").authenticated()
					.requestMatchers("/api/user/details").authenticated()	

					//-------------------- FOR HR -------------------------------------
					.requestMatchers(HttpMethod.POST,"/api/hr/register").permitAll() // allow all to register as hr
        			.requestMatchers(HttpMethod.GET,"/api/hr/{hrId}").hasAuthority("HR") // get hr by hr id
        			.requestMatchers(HttpMethod.GET,"/api/hr").permitAll() // get all hr
        			.requestMatchers(HttpMethod.DELETE,"/api/hr/{hrId}").permitAll() // all get(GetById,GetAll) operations for hr are permit all
        			.requestMatchers(HttpMethod.PUT,"/api/hr/{hrId}").hasAuthority("HR") // only hr can update hr details
        			
					//-------------------- FOR JOB SEEKER -------------------------------------
					.requestMatchers(HttpMethod.POST,"/api/jobseekers/register").permitAll() // register job_seeker (anyone)
        			.requestMatchers(HttpMethod.GET,"/api/jobseekers/{jobSeekerId}").permitAll() // get js by id
        			.requestMatchers(HttpMethod.DELETE,"/api/jobseekers/{jobSeekerId}").permitAll() // delete js by id
        			.requestMatchers(HttpMethod.GET,"/api/jobseekers").permitAll() // get all js
        			.requestMatchers(HttpMethod.PUT,"/api/jobseekers/{jobSeekerId}").hasAuthority("JOB_SEEKER") // only jobseekers can update  details
					
        			// -------------------- FOR JOB POSTING  -------------------------------------
        			.requestMatchers(HttpMethod.POST, "/api/jobs/batch").hasAuthority("HR") // only hr can post jobs(principle interface implemented)
        			.requestMatchers(HttpMethod.PUT, "/api/jobs/{JobId}").hasAuthority("HR") //only hr can update the jobs by job id
        			.requestMatchers(HttpMethod.DELETE, "/api/jobs/{JobId}").hasAuthority("HR") //only hr can delete the jobs
        			.requestMatchers(HttpMethod.GET, "/api/jobs?page=0&size=5").permitAll() // all can view the jobs that hr has posted
        			.requestMatchers(HttpMethod.GET, "/api/jobs/{JobId}").permitAll() // get job by job id
        			.requestMatchers(HttpMethod.GET, "/api/jobs//api/jobs/search?jobTitle={job title}&location={location}&company={company}").permitAll() // search jobs by title, location, and company name

        			//----------------<<<<<<<<<<<<<<<updtae this application apis add permission remaining<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
        			// -------------------- FOR  APPLICATIONS  -------------------------------------
                    .requestMatchers(HttpMethod.POST, "/api/applications/{seekerId}/{jobId}").hasAuthority("JOB_SEEKER") //only job seeker can apply for job
                    .requestMatchers(HttpMethod.PUT, "/api/applications/{AppId}").hasAuthority("JOB_SEEKER") // only job seeker can update the application
                    .requestMatchers(HttpMethod.DELETE, "/api/applications/{AppId}").hasAuthority("JOB_SEEKER") //only js can delete/withdraw application
                    .requestMatchers(HttpMethod.GET, "/api/applications/job/{JobID}").hasAuthority("HR") //view  applications by job ID
                    .requestMatchers(HttpMethod.GET, "/api/applications").hasAuthority("HR") //see all applications
                    .requestMatchers(HttpMethod.GET, "/api/applications/jobseeker/{JobSeekerId}").hasAuthority("JOB_SEEKER") //view appliction by js ID
                    .requestMatchers(HttpMethod.PATCH, "/api/applications/{appID}").hasAuthority("HR") // only hr can update the application as 
                     //REJECTED,SHORTLISTED,INTERVIEW_SCHEDULED,INTERVIEW_COMPLETED,INTERVIEW_COMPLETED_OFFERED,INTERVIEW_COMPLETED_REJECTED,
                    
        			// -------------------- FOR  SEEKER ACTIVITY  -------------------------------------
                    .requestMatchers(HttpMethod.GET, "/api/seeker-activity").permitAll() //here the access is only for admin but for now i have done it permit all>>>>---i will work on it later
                    .requestMatchers(HttpMethod.GET, "/api/seeker-activity/{jobSeekerId}").hasAnyAuthority("JOB_SEEKER") //here the access is even for admin but for job seeker>>>>---i will work on it later
                    .requestMatchers(HttpMethod.DELETE, "/api/seeker-activity/{activityId}").permitAll() //here the access is only for admin but for now i have done it permit all>>>>---i will work on it later

        			// -------------------- FOR APPLICATION UPDATE ----------------------------------
                    .requestMatchers(HttpMethod.GET, "/api/updates/application/{AppId}").hasAnyAuthority("JOB_SEEKER", "HR") // View updates for specific application
                    .requestMatchers(HttpMethod.GET, "/api/updates/jobseeker/{JobSeekerId}").hasAuthority("JOB_SEEKER") // only job seeker can see the updates made by them

                 // -------------------- FOR INTERVIEW ----------------------------------
                    .requestMatchers(HttpMethod.POST, "/api/interviews/application/{appId}").hasAuthority("HR") // HR schedules
                    .requestMatchers(HttpMethod.PUT, "/api/interviews/{appId}").hasAuthority("HR") // HR updates
                    .requestMatchers(HttpMethod.DELETE, "/api/interviews/{interview_ID}").hasAuthority("HR") // HR deletes
                    .requestMatchers(HttpMethod.GET, "/api/interviews/application/{appID}").hasAnyAuthority("HR", "JOB_SEEKER") // View interviews for an application
                    .requestMatchers(HttpMethod.GET, "/api/interviews/{interview ID}").hasAnyAuthority("HR", "JOB_SEEKER") // View specific interview

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

