package com.harshal.springbootblogrestapi.controller;

import com.harshal.springbootblogrestapi.entity.Post;
import com.harshal.springbootblogrestapi.payload.PostDTO;
import com.harshal.springbootblogrestapi.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }

    @PostMapping("/batch")
    public ResponseEntity<List<PostDTO>> createPost(@RequestBody List<PostDTO> postDTO) {
        return new ResponseEntity<>(postService.createPost(postDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
    }

  /* @GetMapping
   public ResponseEntity<List<Post>> getAllPosts() {
       return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
   }*/


    @GetMapping("/page")
    public ResponseEntity<Page> getAllPosts(
            @RequestParam(required = false,defaultValue = "0") int pageNo,
            @RequestParam(required = false,defaultValue = "100") int pageSize,
            @RequestParam(required = false,defaultValue = "id") String sortBy,
            @RequestParam(required = false,defaultValue = "asc") String sortDir) {
        return new ResponseEntity<>(postService.getAllPosts(pageNo,pageSize,sortBy,sortDir), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO, @PathVariable Long id) {
        return ResponseEntity.ok(postService.updatePost(postDTO, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok(String.format("Post with ID:%s deleted successfully!", id.toString()));
    }
}
