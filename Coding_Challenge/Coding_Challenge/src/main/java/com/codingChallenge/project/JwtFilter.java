package com.codingChallenge.project;



import com.codingChallenge.project.service.CustomUserDetailsService;
import com.codingChallenge.project.utility.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException{
        try{
            String username = null;
            String jwt = null;

            /*
            Reading the auth Header
             */

            final String authorizationHeader = request.getHeader("Authorization");

            /*
            Removing bearer from the Header
             */

            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer")){
                jwt = authorizationHeader.substring(7);
                username = jwtUtil.extractUsername(jwt);
            }

            /*
            Checking for the user in the DB and loading its details
             */

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

            /*
            Validating the token by using the verifyToken Method
             */

            boolean isValid = jwtUtil.verifyToken(jwt, username);

            /*
            If the token is valid and not expired then we will create a authentication object and set it in the spring context so that the token will be used as signing key once per request
             */
            if(isValid){
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);

                /*
                Continue down the filter Chain
                 */
                filterChain.doFilter(request, response);
            }


        }
        catch (Exception e){
            System.out.println(e);
            filterChain.doFilter(request, response);
        }
    }





}
