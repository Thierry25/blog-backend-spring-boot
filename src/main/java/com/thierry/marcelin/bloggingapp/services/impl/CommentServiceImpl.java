package com.thierry.marcelin.bloggingapp.services.impl;

import com.thierry.marcelin.bloggingapp.exceptions.BlogAPIException;
import com.thierry.marcelin.bloggingapp.exceptions.ResourceNotFoundException;
import com.thierry.marcelin.bloggingapp.models.Comment;
import com.thierry.marcelin.bloggingapp.models.Post;
import com.thierry.marcelin.bloggingapp.payload.dto.CommentDTO;
import com.thierry.marcelin.bloggingapp.repositories.CommentRepository;
import com.thierry.marcelin.bloggingapp.repositories.PostRepository;
import com.thierry.marcelin.bloggingapp.services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        var currentPost = currentPost(id);
        var comment = mapToEntity(commentDTO);
        comment.setPost(currentPost);
        commentRepository.save(comment);
        return mapToDTO(comment);
    }

    @Override
    public List<CommentDTO> getAllComments(long postId) {
        var currentPost = currentPost(postId);
        return currentPost.getComments()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentById(long postId, long commentId) {
        var currentPost = currentPost(postId);
        var comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        if(!comment.getPost().getId().equals(currentPost.getId()))
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        return mapToDTO(comment);
    }

    @Override
    public CommentDTO updateComment(long postId, long commentId, CommentDTO newData) {
        var currentPost = currentPost(postId);
        var comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found"));
        if(!comment.getPost().getId().equals(currentPost.getId()))
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        comment.setName(newData.getName());
        comment.setEmail(newData.getEmail());
        comment.setBody(newData.getBody());
        return mapToDTO(comment);
    }

    private Comment mapToEntity(CommentDTO commentDTO){
        return new Comment(commentDTO.getName(), commentDTO.getEmail(), commentDTO.getBody());
    }

    private CommentDTO mapToDTO(Comment comment){
        return new CommentDTO(comment.getId(), comment.getName(), comment.getEmail(), comment.getBody());
    }

    private Post currentPost(long id){
        return postRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Post not found"));
    }
}
