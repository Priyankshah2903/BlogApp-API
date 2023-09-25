package com.priyank.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.priyank.api.entites.Post;
import com.priyank.api.payload.PostDto;

public interface PostService {
  PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
  PostDto updatePost(PostDto postDto,Integer postId);
  void deletePost(Integer postId);
  List<PostDto> getAllPost(Integer pageNumber,Integer pageSize,String sortBy);
  PostDto getPostById(Integer postId);
  List<PostDto> getPostsByCategory(Integer categoryId);
  List<PostDto> getPostByUser(Integer userId);
  List<PostDto> searchPosts(String Keyword);
}
