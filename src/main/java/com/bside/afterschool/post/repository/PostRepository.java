package com.bside.afterschool.post.repository;

import com.bside.afterschool.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
