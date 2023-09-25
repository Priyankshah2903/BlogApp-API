package com.priyank.api.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.priyank.api.entites.Comment;
import com.priyank.api.entites.Post;
import com.priyank.api.exceptions.ResourceNotFoundException;
import com.priyank.api.payload.CommentDto;
import com.priyank.api.respositery.CommentRepo;
import com.priyank.api.respositery.PostRepo;
import com.priyank.api.services.CommentService;
@Service
public class CommentImpl implements CommentService {
	@Autowired
     private PostRepo postRepo;
     
     @Autowired
     private CommentRepo commentRepo;
     @Autowired
     private ModelMapper modelMapper;
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","Post Id", postId));
		Comment comment=this.modelMapper.map(commentDto,Comment.class);
		comment.setPost(post);
		
		Comment savedComment=this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment,CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
		Comment comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","Comment Id", commentId));
		this.commentRepo.delete(comment);
		

	}

}
