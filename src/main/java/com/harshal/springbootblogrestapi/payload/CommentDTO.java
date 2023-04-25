package com.harshal.springbootblogrestapi.payload;

import com.harshal.springbootblogrestapi.entity.Comment;
import com.harshal.springbootblogrestapi.entity.Post;
import com.harshal.springbootblogrestapi.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Long id;
    private String email;
    private String body;
    private String name;
    private Post post;

    /*public CommentDTO(Comment comment){
        this.id=comment.getId();
        this.email=comment.getEmail();
        this.body=comment.getBody();
        this.name=comment.getName();
    }*/

    public Comment toEntity(){
        return new ModelMapper().map(this,Comment.class);
    }
}
