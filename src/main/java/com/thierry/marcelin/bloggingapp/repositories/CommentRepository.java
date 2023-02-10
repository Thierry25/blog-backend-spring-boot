package com.thierry.marcelin.bloggingapp.repositories;

import com.thierry.marcelin.bloggingapp.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
