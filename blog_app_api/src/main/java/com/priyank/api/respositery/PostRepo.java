package com.priyank.api.respositery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.priyank.api.entites.Category;
import com.priyank.api.entites.Post;
import com.priyank.api.entites.User;


public interface PostRepo extends JpaRepository<Post,Integer>{
List<Post> findByUser(User user);
List<Post> findByCategory(Category category);

List<Post> findByTitleContaining(String title);

}
