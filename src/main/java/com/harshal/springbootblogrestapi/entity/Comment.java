package com.harshal.springbootblogrestapi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.harshal.springbootblogrestapi.payload.CommentDTO;
import com.harshal.springbootblogrestapi.payload.PostDTO;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comments")
public class Comment {
    @GeneratedValue(generator = "commentIdGenerator",strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "commentIdGenerator",sequenceName = "commentIdSequence",allocationSize = 1)
    @Id
    private Long id;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String body;
    @Column(nullable = false)
    private String name;
    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "post_id",nullable = false)
    private Post post;

  /*  public Comment(CommentDTO commentDTO) {
        this.id=commentDTO.getId();
        this.body=commentDTO.getBody();
        this.name=commentDTO.getName();
        this.email=commentDTO.getEmail();
    }*/

    public CommentDTO toDTO(){
        return new ModelMapper().map(this,CommentDTO.class);
    }

    public Comment updateFromDTO(CommentDTO commentDTO){
        this.setEmail(commentDTO.getEmail());
        this.setBody(commentDTO.getBody());
        this.setName(commentDTO.getName());
       /* this.setPost(commentDTO.getPost());*/
        return this;
    }

   @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(email, comment.email) && Objects.equals(body, comment.body) && Objects.equals(name, comment.name) && Objects.equals(post, comment.post);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, body, name, post);
    }
}
