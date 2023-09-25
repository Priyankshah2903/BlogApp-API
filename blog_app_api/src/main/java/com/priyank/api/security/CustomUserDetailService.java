package com.priyank.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.priyank.api.entites.User;
import com.priyank.api.exceptions.ResourceNotFoundException;
import com.priyank.api.respositery.UserRepo;
@Service
public class CustomUserDetailService implements UserDetailsService{
	@Autowired
     private UserRepo userRepo;
     
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	User user=this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User","email: " +username,0));
	
		return user;
	}

}
