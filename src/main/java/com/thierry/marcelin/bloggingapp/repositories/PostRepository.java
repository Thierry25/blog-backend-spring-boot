package com.thierry.marcelin.bloggingapp.repositories;

import com.thierry.marcelin.bloggingapp.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
