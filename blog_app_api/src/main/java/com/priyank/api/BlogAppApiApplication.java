package com.priyank.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.priyank.api.entites.Role;
import com.priyank.api.respositery.RoleRepo;

import io.swagger.annotations.Info;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.validation.constraints.AssertFalse.List;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.This;

@SpringBootApplication
@SecurityScheme(name = "Priyankapi", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(title = "Blog Application API", version = "2.0", description = "Project Created by Priyank Shah"))
public class BlogAppApiApplication implements CommandLineRunner{

    @Autowired 
	private PasswordEncoder passwordEncoder;
 
    
	public static void main(String[] args) {
		SpringApplication.run(BlogAppApiApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println(this.passwordEncoder.encode("abc"));
	}
	

}
