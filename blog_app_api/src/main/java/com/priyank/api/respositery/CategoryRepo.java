package com.priyank.api.respositery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.priyank.api.entites.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
