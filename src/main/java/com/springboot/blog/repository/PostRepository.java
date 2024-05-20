package com.springboot.blog.repository;

import com.springboot.blog.dto.PostDto;
import com.springboot.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// this interface internally use **SimpleJpaRepository** class implements the JpaRepository interface for all the
// CRUD operations
// es por lo anterior que no se necesita colocar @Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByCategoryId(Long categoryId);
}
