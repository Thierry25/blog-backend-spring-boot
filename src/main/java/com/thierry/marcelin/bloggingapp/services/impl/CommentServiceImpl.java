package com.thierry.marcelin.bloggingapp.services.impl;

import com.thierry.marcelin.bloggingapp.exceptions.ResourceNotFoundException;
import com.thierry.marcelin.bloggingapp.models.Comment;
import com.thierry.marcelin.bloggingapp.payload.dto.CommentDTO;
import com.thierry.marcelin.bloggingapp.repositories.CommentRepository;
import com.thierry.marcelin.bloggingapp.repositories.PostRepository;
import com.thierry.marcelin.bloggingapp.services.CommentService;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDTO createNewComment(long id, CommentDTO commentDTO) {
        var currentPost = postRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Post not found"));
        var comment = mapToEntity(commentDTO);
        comment.setPost(currentPost);
        commentRepository.save(comment);
        return mapToDTO(comment);
    }

    private Comment mapToEntity(CommentDTO commentDTO){
        return new Comment(commentDTO.getName(), commentDTO.getEmail(), commentDTO.getBody());
    }

    private CommentDTO mapToDTO(Comment comment){
        return new CommentDTO(comment.getId(), comment.getName(), comment.getEmail(), comment.getBody());
    }
}
