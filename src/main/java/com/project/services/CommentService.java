package com.project.services;

import com.project.payloads.CommentDto;

import java.util.List;

public interface CommentService {
    public CommentDto addComment(CommentDto commentDto, Integer postId);

    public CommentDto updateComment(CommentDto commentDto, Integer commentId);

    public boolean deleteComment(Integer commentId);

    public List<CommentDto> getAllCommentsByPostId(Integer PostId);
}
