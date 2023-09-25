package com.priyank.api.respositery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.priyank.api.entites.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

}
