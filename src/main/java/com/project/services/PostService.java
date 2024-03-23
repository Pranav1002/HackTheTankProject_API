package com.project.services;

import com.project.exceptions.ResourceNotFoundException;
import com.project.models.Post;
import com.project.models.User;
import com.project.payloads.PostDto;

import java.util.List;

public interface PostService {

    public PostDto addPost(PostDto postDto, Integer userId);

    public PostDto updatePost(PostDto postDto, Integer postId);

    public boolean deletePost(Integer postId);

    public PostDto getPostById(Integer postId);

    public List<PostDto> getPostsByUserId(Integer userId);

    public List<PostDto> getAllPosts();

}
