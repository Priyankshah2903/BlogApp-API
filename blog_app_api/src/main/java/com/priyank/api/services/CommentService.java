package com.priyank.api.services;

import com.priyank.api.payload.CommentDto;

public interface CommentService {
CommentDto createComment(CommentDto commentDto,Integer postId);
void deleteComment(Integer commentId);
}
