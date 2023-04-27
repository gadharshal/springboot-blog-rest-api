package com.harshal.springbootblogrestapi.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.harshal.springbootblogrestapi.payload.PostDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "posts")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"title"})})
public class Post {

    @Id
    @GeneratedValue(generator = "postIdGenerator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "postIdGenerator", sequenceName = "postIdSequence", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String heading;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String content;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Comment> comments = new HashSet<>();

   /* public Post(PostDTO postDTO){
        this.setId(postDTO.getId());
        this.setTitle(postDTO.getTitle());
        this.setHeading(postDTO.getHeading());
        this.setDescription(postDTO.getDescription());
        this.setContent(postDTO.getContent());
    }*/

   /* public Set<Comment> getComments() {
        return comments;
    }*/

    @JsonManagedReference
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public PostDTO toDTO() {
        return new ModelMapper().map(this, PostDTO.class);
    }

    public Post updateFromDTO(PostDTO postDTO) {
        this.setTitle(postDTO.getTitle());
        this.setHeading(postDTO.getHeading());
        this.setDescription(postDTO.getDescription());
        this.setContent(postDTO.getContent());
        //this.setComments(postDTO.getComments());
        return this;
    }

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) && Objects.equals(title, post.title) && Objects.equals(heading, post.heading) && Objects.equals(description, post.description) && Objects.equals(content, post.content) && Objects.equals(comments, post.comments);
    }*/

    /*@Override
    public int hashCode() {
        return Objects.hash(id, title, heading, description, content, comments);
    }*/

   /* @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", heading='" + heading + '\'' +
                ", description='" + description + '\'' +
                ", content='" + content + '\'' +
                ", comments=" + comments +
                '}';
    }*/
}
