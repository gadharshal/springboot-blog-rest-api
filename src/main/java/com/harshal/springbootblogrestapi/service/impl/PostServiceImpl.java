package com.harshal.springbootblogrestapi.service.impl;

import com.harshal.springbootblogrestapi.entity.Post;
import com.harshal.springbootblogrestapi.exception.ResourceNotFoundException;
import com.harshal.springbootblogrestapi.payload.PostDTO;
import com.harshal.springbootblogrestapi.repository.PostRepository;
import com.harshal.springbootblogrestapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;


    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        return postRepository.save(postDTO.toEntity()).toDTO();
    }

    @Override
    public List<PostDTO> createPost(List<PostDTO> postDTO) {
        return postRepository.saveAll(postDTO.stream().map(p -> p.toEntity()).toList()).stream().map(p->p.toDTO()).toList();
    }

    public List<PostDTO> getAllPosts(){
        return postRepository.findAll().stream().map(p->p.toDTO()).collect(Collectors.toList());
    }

    /*public List<Post> getAllPosts(){
        List<Post> posts=postRepository.findAll();
      //  posts.stream().forEach(System.out::println);
        return posts;
    }*/

    public Page getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir){
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.toString())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(pageNo,pageSize,sort);
        return postRepository.findAll(pageable);
    }

   /* @Override
    public List<PostDTO> getAllPost() {
        List<Post> posts = postRepository.findAll();
        List<PostDTO> postDTOS = posts.stream().map(p -> p.toDTO()).collect(Collectors.toList());
        return postDTOS;
    }*/

    @Override
    public PostDTO getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id)).toDTO();
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long id) {
        return postRepository.save(postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id)).updateFromDTO(postDTO)).toDTO();
    }

    @Override
    public void deletePost(Long id) {
        postRepository.delete(postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id)));
    }
}
