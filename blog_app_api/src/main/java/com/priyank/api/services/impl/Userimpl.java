package com.priyank.api.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.priyank.api.entites.Role;
import com.priyank.api.entites.User;
import com.priyank.api.payload.UserDto;
import com.priyank.api.respositery.RoleRepo;
import com.priyank.api.respositery.UserRepo;
import com.priyank.api.services.UserService;
import com.priyank.api.exceptions.*;
@Service
public class Userimpl  implements UserService {
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private RoleRepo roleRepo;

	@Override
	public UserDto createUser(UserDto userDto) {
		User  user=this.dtoToUser(userDto);
		User savedUser=this.userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
		user.setUser_name(userDto.getUser_name());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		user.setAbout(userDto.getAbout());
		
		User updatedUser=this.userRepo.save(user);
		UserDto userDto1=this.userToDto(updatedUser);
		
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) {
	     User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
	     
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=this.userRepo.findAll();
		List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect((Collectors.toList()));
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        this.userRepo.delete(user);
	}
	
	public User dtoToUser(UserDto userDto) {
		User user=this.modelMapper.map(userDto,User.class);
//		user.setUser_id(userDto.getUser_id());
//		user.setUser_name(userDto.getUser_name());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
		return user;
	}
	public UserDto userToDto(User user) {
		UserDto userDto=this.modelMapper.map(user,UserDto.class);
//		userDto.setUser_id(user.getUser_id());
//		userDto.setUser_name(user.getUser_name());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		 
	User user=	this.modelMapper.map(userDto,User.class);
	user.setPassword(this.passwordEncoder.encode(user.getPassword()));
	Role role=this.roleRepo.findById(502).get();
	user.getRoles().add(role);
	User newUser=this.userRepo.save(user);
		return this.modelMapper.map(newUser, UserDto.class);
	}


}
