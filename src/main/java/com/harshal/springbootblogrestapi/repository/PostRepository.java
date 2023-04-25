package com.harshal.springbootblogrestapi.repository;

import com.harshal.springbootblogrestapi.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
