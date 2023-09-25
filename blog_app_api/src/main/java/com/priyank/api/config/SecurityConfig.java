package com.priyank.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.priyank.api.entites.User;
import com.priyank.api.security.CustomUserDetailService;

import jakarta.servlet.Filter;

@Configuration
@EnableWebMvc
@EnableWebSecurity
public class SecurityConfig {
	@Autowired
	private CustomUserDetailService customUserDetailService;
	 @SuppressWarnings("deprecation")
	@Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		 http.csrf(csrf -> csrf.disable())
         .authorizeHttpRequests().
         anyRequest().
         authenticated().
         and().httpBasic();
	              
	  http.authenticationProvider(daoAuthenticationProvider());
	        return http.build();
	    }
	 public DaoAuthenticationProvider daoAuthenticationProvider() {
		 DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		 provider.setUserDetailsService(this.customUserDetailService);
		 provider.setPasswordEncoder(passwordEncoder());
		 return provider;
	 }
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
	
	        return builder.getAuthenticationManager();
	    }
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	 
}
