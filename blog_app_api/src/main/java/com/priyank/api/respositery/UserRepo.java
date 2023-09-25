package com.priyank.api.respositery;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.priyank.api.entites.User;
import java.util.List;


public interface UserRepo extends JpaRepository<User,Integer>{
         Optional<User> findByEmail(String email);
}
