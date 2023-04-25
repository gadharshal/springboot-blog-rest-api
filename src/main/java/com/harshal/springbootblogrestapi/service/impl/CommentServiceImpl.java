package com.harshal.springbootblogrestapi.service.impl;

import com.harshal.springbootblogrestapi.entity.Comment;
import com.harshal.springbootblogrestapi.entity.Post;
import com.harshal.springbootblogrestapi.exception.BlogAPIException;
import com.harshal.springbootblogrestapi.payload.CommentDTO;
import com.harshal.springbootblogrestapi.repository.CommentRepository;
import com.harshal.springbootblogrestapi.repository.PostRepository;
import com.harshal.springbootblogrestapi.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.harshal.springbootblogrestapi.utils.BlogRestApiutils.resourceNotFoundExceptionSupplier;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public CommentDTO createComment(Long postId, CommentDTO commentDTO) {
        Post post = postRepository.findById(postId).orElseThrow(resourceNotFoundExceptionSupplier("Post", "id", postId));
        Comment comment = commentDTO.toEntity();
        comment.setPost(post);
        return commentRepository.save(comment).toDTO();
    }

    @Override
    public List<CommentDTO> getCommentByPostId(Long postId) {
        return commentRepository.findByPostId(postId).stream().map(c -> c.toDTO()).toList();
    }

    @Override
    public CommentDTO getCommentById(Long commentId) {
        return commentRepository.findById(commentId).get().toDTO();
    }

    @Override
    public CommentDTO getCommentById(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(resourceNotFoundExceptionSupplier("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(resourceNotFoundExceptionSupplier("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doesn't belong to the Post with PostId " + postId);
        }

        return comment.toDTO();
    }

    @Override
    public CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO) {

        Post post = postRepository.findById(postId).orElseThrow(resourceNotFoundExceptionSupplier("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(resourceNotFoundExceptionSupplier("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doesn't belong to the Post with PostId " + postId);
        }

        /*commentDTO.setId(commentId);
        comment=commentDTO.toEntity();
        comment.setPost(post);*/
        return commentRepository.save(comment.updateFromDTO(commentDTO)).toDTO();
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(resourceNotFoundExceptionSupplier("Post", "id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(resourceNotFoundExceptionSupplier("Comment", "id", commentId));

        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment doesn't belong to the Post with PostId " + postId);
        }

        commentRepository.delete(comment);
    }


}
