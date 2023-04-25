package com.harshal.springbootblogrestapi.payload;

import com.harshal.springbootblogrestapi.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {
    private Long id;
    private String title;
    private String heading;
    private String description;
    private String content;
    private Set<CommentDTO> comments;

    /*public PostDTO(Post post){
        this.setId(post.getId());
        this.setTitle(post.getTitle());
        this.setHeading(post.getHeading());
        this.setDescription(post.getDescription());
        this.setContent(post.getContent());
    }*/

    public Post toEntity(){

       return new ModelMapper().map(this,Post.class);
       // return new Post(this);
    }
}
