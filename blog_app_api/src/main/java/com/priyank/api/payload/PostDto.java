package com.priyank.api.payload;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.priyank.api.entites.Category;
import com.priyank.api.entites.Comment;
import com.priyank.api.entites.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	private Integer postId;
	private String title;
    private String content;
    private String imageName;
    private CategoryDto category;
    private UserDto user;
    
    private Set<CommentDto> comments=new HashSet<>();
}
