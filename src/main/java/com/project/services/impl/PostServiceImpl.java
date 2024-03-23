package com.project.services.impl;

import com.project.exceptions.ResourceNotFoundException;
import com.project.models.Post;
import com.project.models.User;
import com.project.payloads.PostDto;
import com.project.repositories.PostRepository;
import com.project.repositories.UserRepository;
import com.project.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PostDto addPost(PostDto postDto, Integer userId)
    {
        Post post = modelMapper.map(postDto, Post.class);

        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));

        post.setUser(user);

        return modelMapper.map(post, PostDto.class);
    }

    public PostDto updatePost(PostDto postDto, Integer postId)
    {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
        post.setContent(postDto.getContent());
        postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    public boolean deletePost(Integer postId)
    {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
        postRepository.delete(post);
        return true;
    }

    public PostDto getPostById(Integer postId)
    {
        Post post = postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Id", postId));
        return modelMapper.map(post, PostDto.class);
    }

    public List<PostDto> getPostsByUserId(Integer userId)
    {
        List<Post> posts = postRepository.findByUser_UserId(userId);

        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).toList();

        return postDtos;
    }

    public List<PostDto> getAllPosts()
    {
        List<Post> posts = postRepository.findAll();

        List<PostDto> postDtos = posts.stream().map(post -> modelMapper.map(post, PostDto.class)).toList();

        return postDtos;
    }

}
