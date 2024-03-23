package com.project.services.impl;

import com.project.exceptions.ResourceNotFoundException;
import com.project.models.Comment;
import com.project.models.Post;
import com.project.payloads.CommentDto;
import com.project.repositories.CommentRepository;
import com.project.repositories.PostRepository;
import com.project.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto addComment(CommentDto commentDto, Integer postId) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
        comment.setPost(post);
        commentRepository.save(comment);
        return modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto, Integer commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "Id", commentId));
        comment.setText(commentDto.getText());
        commentRepository.save(comment);
        return modelMapper.map(comment, CommentDto.class);
    }

    @Override
    public boolean deleteComment(Integer commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("comment", "Id", commentId));
        commentRepository.delete(comment);
        return true;
    }

    @Override
    public List<CommentDto> getAllCommentsByPostId(Integer postId) {
        List<Comment> comments = commentRepository.findByPost_PostId(postId);

        List<CommentDto> commentDtos = comments.stream().map(comment -> modelMapper.map(comment, CommentDto.class)).toList();

        return commentDtos;
    }
}
