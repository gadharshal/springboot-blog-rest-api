package com.harshal.springbootblogrestapi.controller;

import com.harshal.springbootblogrestapi.entity.Comment;
import com.harshal.springbootblogrestapi.payload.CommentDTO;
import com.harshal.springbootblogrestapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.OutputKeys;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService ){
        this.commentService=commentService;
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable Long postId, @RequestBody CommentDTO commentDTO){
        return new ResponseEntity<>(commentService.createComment(postId,commentDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByPostId(@PathVariable Long postId){
        return new ResponseEntity<>(commentService.getCommentByPostId(postId), HttpStatus.OK);
    }

    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long commentId){
        return new ResponseEntity<>(commentService.getCommentById(commentId), HttpStatus.OK);
    }

    @GetMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getCommentsByPostId(@PathVariable Long postId,@PathVariable Long commentId){
        return new ResponseEntity<>(commentService.getCommentById(postId,commentId), HttpStatus.OK);
    }

    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long postId,@PathVariable Long commentId,@RequestBody CommentDTO commentDTO){
        return  ResponseEntity.ok(commentService.updateComment(postId,commentId,commentDTO));
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long postId,@PathVariable Long commentId){
        commentService.deleteComment(postId,commentId);
        return new ResponseEntity<>("Comment with Id:"+commentId+" deleted successfully!",HttpStatus.OK);
    }

}
