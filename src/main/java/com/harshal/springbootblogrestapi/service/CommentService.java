package com.harshal.springbootblogrestapi.service;

import com.harshal.springbootblogrestapi.entity.Comment;
import com.harshal.springbootblogrestapi.payload.CommentDTO;

import java.util.List;

public interface CommentService {

    CommentDTO createComment(Long postId, CommentDTO commentDTO);

    List<CommentDTO> getCommentByPostId(Long postId);

    CommentDTO getCommentById(Long commentId);

    CommentDTO getCommentById(Long postId,Long commentId);

    CommentDTO updateComment(Long postId,Long commentId,CommentDTO commentDTO);

    void deleteComment(Long postId,Long commentId);

}
