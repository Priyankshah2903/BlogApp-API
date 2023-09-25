package com.priyank.api.respositery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.priyank.api.entites.Role;

public interface RoleRepo  extends JpaRepository<Role,Integer> {

}
