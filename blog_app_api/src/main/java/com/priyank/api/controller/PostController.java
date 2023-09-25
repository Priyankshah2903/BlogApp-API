package com.priyank.api.controller;

import java.awt.PageAttributes.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.swing.LayoutFocusTraversalPolicy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.util.StreamUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.priyank.api.payload.ApiResponse;
import com.priyank.api.payload.PostDto;
import com.priyank.api.services.FileService;
import com.priyank.api.services.PostService;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/")
public class PostController {
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileservice;
	@Value("${project.image}")
	private String pathString;
	
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,@PathVariable Integer userId,@PathVariable Integer categoryId){
		PostDto createPost=this.postService.createPost(postDto,userId,categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
	}
      @GetMapping("/user/{userId}/posts")
     public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
    	  List<PostDto> postsDtos=this.postService.getPostByUser(userId);
    	  return new ResponseEntity<List<PostDto>>(postsDtos,HttpStatus.OK);
    	  
      }
      @GetMapping("/category/{categoryId}/posts")
      public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
     	  List<PostDto> postsDtos=this.postService.getPostsByCategory(categoryId);
     	  return new ResponseEntity<List<PostDto>>(postsDtos,HttpStatus.OK);
     	  
       }
      
      @GetMapping("/posts")
      public ResponseEntity<List<PostDto>> getAllPost(@RequestParam(value = "pageNumber",defaultValue = "0",required = false)Integer pageNumber,@RequestParam(value = "pageSize",defaultValue = "10",required = false)Integer pageSize,
    		                                           @RequestParam(value = "sortBy",defaultValue = "postId",required = false) String sortBy){
    	List<PostDto> allPostDtos=this.postService.getAllPost(pageNumber,pageSize,sortBy);
    	return new ResponseEntity<List<PostDto>>(allPostDtos,HttpStatus.OK);
      }
      
      @GetMapping("/posts/{postId}")
      public ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
    	PostDto PostDto=this.postService.getPostById(postId);
    	return new ResponseEntity<PostDto>(PostDto,HttpStatus.OK);
      }
      @DeleteMapping("/posts/{postId}")
      public ApiResponse deletePost(@PathVariable Integer postId) {
    	  this.postService.deletePost(postId);
    	  return new ApiResponse("Post is deleted",true);
      }
      @PutMapping("/posts/{postId}")
      public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
    	PostDto updatePostDto=this.postService.updatePost(postDto,postId);
    	return new ResponseEntity<PostDto>(updatePostDto,HttpStatus.OK);
      }
      @GetMapping("/posts/search/{keywords}")
      public ResponseEntity<List<PostDto>> searchPostByTitle(@PathVariable("keywords") String keywords){
    	List<PostDto> PostDto=this.postService.searchPosts(keywords);
    	return new ResponseEntity<List<PostDto>>(PostDto,HttpStatus.OK);
      }
      @PostMapping("/post/image/upload/{postId}")
      public ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile image,@PathVariable Integer postId) throws IOException{
    	 String fileNameString= this.fileservice.uploadImage(pathString, image);
    	 PostDto postDto=this.postService.getPostById(postId);
    	 postDto.setImageName(fileNameString);
    	PostDto updatePostDto= this.postService.updatePost(postDto, postId);
    	 
    	 return new ResponseEntity<PostDto>(updatePostDto,HttpStatus.OK);
      }
      
      @GetMapping(value="post/image/{imageName}",produces=org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
      public void downloadImage(@PathVariable("imageName") String imageName,HttpServletResponse response)throws IOException{
    InputStream  resource=this.fileservice.getResource(pathString, imageName)  ;
    response.setContentType(org.springframework.http.MediaType.IMAGE_JPEG_VALUE);
    org.springframework.util.StreamUtils.copy(resource,response.getOutputStream());
    }
}
