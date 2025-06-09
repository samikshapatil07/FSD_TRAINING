
package com.demo.CodingChallenge.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.CodingChallenge.model.User;
import com.demo.CodingChallenge.repository.UserRepository;


@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Fetch User by given username 
		User user = userRepository.findByUsername(username);
		if(user == null)
			throw new UsernameNotFoundException("Invalid Credentials");
		
		// Convert your Role into Authority as spring works with authority
		SimpleGrantedAuthority sga = new SimpleGrantedAuthority(user.getRole()); 
		
		// Add this SimpleGrantedAuthority object into the List now 
		List<GrantedAuthority> list = List.of(sga);
		
		// Convert our User to Spring's User that is UserDetails
		org.springframework.security.core.userdetails.User springuser = 
				new org.springframework.security.core.userdetails.User
						(user.getUsername(), 
						 user.getPassword(), 
						 list);
		
		return springuser;
	}

}
