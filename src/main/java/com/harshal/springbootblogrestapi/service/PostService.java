package com.harshal.springbootblogrestapi.service;

import com.harshal.springbootblogrestapi.entity.Post;
import com.harshal.springbootblogrestapi.payload.PostDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO);

    List<PostDTO> createPost(List<PostDTO> postDTO);


    public List<PostDTO> getAllPosts();

    /*public List<Post> getAllPosts();*/


    public Page getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDTO getPostById(Long id);

    PostDTO updatePost(PostDTO postDTO, Long id);

    void deletePost(Long id);


}
